const API_BASE = 'http://localhost:8080';
let isLoadingMedicos = false, isLoadingPacientes = false, isLoadingTurnos = false, editandoId = null, editandoTipo = null;

function mostrarSeccion(seccion) {
    document.querySelectorAll('.section').forEach(s => {s.classList.remove('active'); s.classList.add('hidden');});
    document.getElementById(seccion).classList.remove('hidden');
    document.getElementById(seccion).classList.add('active');
    document.querySelectorAll('.nav-btn').forEach(btn => btn.classList.remove('active'));
    document.querySelector(`[onclick="mostrarSeccion('${seccion}')"]`)?.classList.add('active');
    if (seccion === 'medicos') cargarMedicos();
    else if (seccion === 'pacientes') cargarPacientes();
    else cargarTurnos();
}

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('formMedico').onsubmit = handleMedicoSubmit;
    document.getElementById('formPaciente').onsubmit = handlePacienteSubmit;
    document.getElementById('formTurno').onsubmit = handleTurnoSubmit;
    cargarMedicos();
});

async function handleMedicoSubmit(e) {
    e.preventDefault();
    const dto = {
        nombre: document.getElementById('medicoNombre').value,
        apellido: document.getElementById('medicoApellido').value,
        dni: document.getElementById('medicoDni').value,
        nroTelefono: document.getElementById('medicoTelefono').value || null,
        matricula: document.getElementById('medicoMatricula').value,
        especialidad: document.getElementById('medicoEspecialidad').value
    };
    
    try {
        const res = await fetch(`${API_BASE}/medicos`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(dto)
        });
        if (!res.ok) throw new Error(await res.text());
        mostrarToast('✅ Médico agregado');
        limpiarFormMedico();
        await cargarMedicos();
    } catch(e) {
        mostrarToast('❌ ' + e.message, 'error');
    }
}

async function handlePacienteSubmit(e) {
    e.preventDefault();
    const dto = {
        nombre: document.getElementById('pacienteNombre').value,
        apellido: document.getElementById('pacienteApellido').value,
        dni: document.getElementById('pacienteDni').value,
        nroTelefono: document.getElementById('pacienteTelefono').value || null,
        fechaNacimiento: document.getElementById('pacienteFechaNac').value || null,
        obraSocial: document.getElementById('pacienteObraSocial').value || null
    };
    
    try {
        const res = await fetch(`${API_BASE}/pacientes`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(dto)
        });
        if (!res.ok) throw new Error(await res.text());
        mostrarToast('✅ Paciente agregado');
        limpiarFormPaciente();
        await cargarPacientes();
    } catch(e) {
        mostrarToast('❌ ' + e.message, 'error');
    }
}

async function handleTurnoSubmit(e) {
    e.preventDefault();
    const dto = {
        idPaciente: parseInt(document.getElementById('turnoPacienteId').value),
        idMedico: parseInt(document.getElementById('turnoMedicoId').value),
        fechaHora: document.getElementById('turnoFecha').value
    };
    
    try {
        const res = await fetch(`${API_BASE}/turnos`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(dto)
        });
        if (!res.ok) throw new Error(await res.text());
        mostrarToast('✅ Turno creado');
        limpiarFormTurno();
        await cargarTurnos();
    } catch(e) {
        mostrarToast('❌ ' + e.message, 'error');
    }
}

// 🔥 UPDATE FUNCTIONS
async function handleMedicoUpdate(e) {
    e.preventDefault();
    const dto = {
        nombre: document.getElementById('medicoNombre').value,
        apellido: document.getElementById('medicoApellido').value,
        dni: document.getElementById('medicoDni').value,
        nroTelefono: document.getElementById('medicoTelefono').value || null,
        matricula: document.getElementById('medicoMatricula').value,
        especialidad: document.getElementById('medicoEspecialidad').value
    };
    
    try {
        const res = await fetch(`${API_BASE}/medicos/${editandoId}`, {
            method: 'PATCH',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(dto)
        });
        if (!res.ok) throw new Error(await res.text());
        mostrarToast('✅ Médico actualizado');
        limpiarFormMedico();
        await cargarMedicos();
    } catch(e) {
        mostrarToast('❌ ' + e.message, 'error');
    }
}

async function handlePacienteUpdate(e) {
    e.preventDefault();
    const dto = {
        nombre: document.getElementById('pacienteNombre').value,
        apellido: document.getElementById('pacienteApellido').value,
        dni: document.getElementById('pacienteDni').value,
        nroTelefono: document.getElementById('pacienteTelefono').value || null,
        fechaNacimiento: document.getElementById('pacienteFechaNac').value || null,
        obraSocial: document.getElementById('pacienteObraSocial').value || null
    };
    
    try {
        const res = await fetch(`${API_BASE}/pacientes/${editandoId}`, {
            method: 'PATCH',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(dto)
        });
        if (!res.ok) throw new Error(await res.text());
        mostrarToast('✅ Paciente actualizado');
        limpiarFormPaciente();
        await cargarPacientes();
    } catch(e) {
        mostrarToast('❌ ' + e.message, 'error');
    }
}

async function handleTurnoUpdate(e) {
    e.preventDefault();
    const dto = {
        idPaciente: parseInt(document.getElementById('turnoPacienteId').value),
        idMedico: parseInt(document.getElementById('turnoMedicoId').value),
        fechaHora: document.getElementById('turnoFecha').value
    };
    
    try {
        const res = await fetch(`${API_BASE}/turnos/${editandoId}`, {
            method: 'PATCH',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(dto)
        });
        if (!res.ok) throw new Error(await res.text());
        mostrarToast('✅ Turno actualizado');
        limpiarFormTurno();
        await cargarTurnos();
    } catch(e) {
        mostrarToast('❌ ' + e.message, 'error');
    }
}

async function cargarMedicos() {
    if (isLoadingMedicos) return; isLoadingMedicos = true;
    try {
        const res = await fetch(`${API_BASE}/medicos`);
        const medicos = await res.json();
        renderLista('listaMedicos', medicos, 'medico');
    } catch(e) {
        document.getElementById('listaMedicos').innerHTML = '<p class="text-center text-red-500 py-8">Error cargando médicos</p>';
    } finally { isLoadingMedicos = false; }
}

async function cargarPacientes() {
    if (isLoadingPacientes) return; isLoadingPacientes = true;
    try {
        const res = await fetch(`${API_BASE}/pacientes`);
        const pacientes = await res.json();
        renderLista('listaPacientes', pacientes, 'paciente');
    } catch(e) {
        document.getElementById('listaPacientes').innerHTML = '<p class="text-center text-red-500 py-8">Error cargando pacientes</p>';
    } finally { isLoadingPacientes = false; }
}

async function cargarTurnos() {
    if (isLoadingTurnos) return; isLoadingTurnos = true;
    try {
        const res = await fetch(`${API_BASE}/turnos`);
        const turnos = await res.json();
        renderLista('listaTurnos', turnos, 'turno');
    } catch(e) {
        document.getElementById('listaTurnos').innerHTML = '<p class="text-center text-red-500 py-8">Error cargando turnos</p>';
    } finally { isLoadingTurnos = false; }
}

function renderLista(containerId, items, tipo) {
    const container = document.getElementById(containerId);
    if (!items?.length) {
        container.innerHTML = `<p class="text-center text-gray-500 py-8">No hay ${tipo}s registrados</p>`;
        return;
    }
    container.innerHTML = items.map(item => {
        const id = item.id || item.idPersona || item.idTurno;
        const detalles = Object.entries(item)
            .filter(([k]) => !['id','idPersona','idTurno'].includes(k))
            .map(([k,v]) => `${k.charAt(0).toUpperCase()+k.slice(1)}: ${v||'N/A'}`).join(' | ');
        return `<div class="list-item">
            <div class="item-content">
                <h4>${item.nombre || `ID: ${id}`}</h4>
                <p>${detalles}</p>
            </div>
            <div class="item-actions">
                <button class="btn-action btn-edit" onclick="editar(${id}, '${tipo}')" title="Editar"><i class="fas fa-edit"></i></button>
                <button class="btn-action btn-delete" onclick="eliminar(${id}, '${tipo}')" title="Eliminar"><i class="fas fa-trash"></i></button>
            </div>
        </div>`;
    }).join('');
}

// 🔥 EDITAR COMPLETO (CON TURNOS)
async function editar(id, tipo) {
    console.log('🔥 EDITAR:', id, tipo);
    editandoId = id; editandoTipo = tipo;
    
    try {
        const res = await fetch(`${API_BASE}/${tipo}s/${id}`);
        if (!res.ok) throw new Error('No encontrado');
        const item = await res.json();
        
        if (tipo === 'medico') {
            document.getElementById('medicoNombre').value = item.nombre || '';
            document.getElementById('medicoApellido').value = item.apellido || '';
            document.getElementById('medicoDni').value = item.dni || '';
            document.getElementById('medicoTelefono').value = item.nroTelefono || '';
            document.getElementById('medicoMatricula').value = item.matricula || '';
            document.getElementById('medicoEspecialidad').value = item.especialidad || '';
            mostrarSeccion('medicos');
            
            const btn = document.querySelector('#formMedico button[type="submit"]');
            btn.textContent = '✏️ Actualizar Médico';
            btn.onsubmit = null;
            btn.onclick = handleMedicoUpdate;
        } 
        else if (tipo === 'paciente') {
            document.getElementById('pacienteNombre').value = item.nombre || '';
            document.getElementById('pacienteApellido').value = item.apellido || '';
            document.getElementById('pacienteDni').value = item.dni || '';
            document.getElementById('pacienteTelefono').value = item.nroTelefono || '';
            document.getElementById('pacienteFechaNac').value = item.fechaNacimiento || '';
            document.getElementById('pacienteObraSocial').value = item.obraSocial || '';
            mostrarSeccion('pacientes');
            
            const btn = document.querySelector('#formPaciente button[type="submit"]');
            btn.textContent = '✏️ Actualizar Paciente';
            btn.onsubmit = null;
            btn.onclick = handlePacienteUpdate;
        }
        else if (tipo === 'turno') {
            document.getElementById('turnoPacienteId').value = item.idPaciente || '';
            document.getElementById('turnoMedicoId').value = item.idMedico || '';
            document.getElementById('turnoFecha').value = item.fechaHora ? item.fechaHora.split('T')[0] : '';
            mostrarSeccion('turnos');
            
            const btn = document.querySelector('#formTurno button[type="submit"]');
            btn.textContent = '✏️ Actualizar Turno';
            btn.onsubmit = null;
            btn.onclick = handleTurnoUpdate;
        }
        mostrarToast(`✏️ Editando ${tipo} ${id}`);
    } catch(e) {
        console.error('❌', e);
        mostrarToast('❌ Error al cargar', 'error');
    }
}

async function eliminar(id, tipo) {
    if (confirm(`¿Eliminar este ${tipo}?`)) {
        try {
            await fetch(`${API_BASE}/${tipo}s/${id}`, {method: 'DELETE'});
            mostrarToast(`✅ ${tipo} eliminado`);
            if (document.getElementById('medicos').classList.contains('active')) cargarMedicos();
            else if (document.getElementById('pacientes').classList.contains('active')) cargarPacientes();
            else cargarTurnos();
        } catch(e) {
            mostrarToast('❌ Error al eliminar', 'error');
        }
    }
}

function limpiarForm(prefix) {
    document.querySelectorAll(`#form${prefix.charAt(0).toUpperCase() + prefix.slice(1)} input`).forEach(i => i.value = '');
}

function limpiarFormMedico() {
    limpiarForm('medico');
    const btn = document.querySelector('#formMedico button[type="submit"]');
    btn.textContent = '➕ Agregar Médico';
    btn.onclick = null;
    document.getElementById('formMedico').onsubmit = handleMedicoSubmit;
    editandoId = null; editandoTipo = null;
}

function limpiarFormPaciente() {
    limpiarForm('paciente');
    const btn = document.querySelector('#formPaciente button[type="submit"]');
    btn.textContent = '➕ Agregar Paciente';
    btn.onclick = null;
    document.getElementById('formPaciente').onsubmit = handlePacienteSubmit;
    editandoId = null; editandoTipo = null;
}

function limpiarFormTurno() {
    limpiarForm('turno');
    const btn = document.querySelector('#formTurno button[type="submit"]');
    btn.textContent = '➕ Nuevo Turno';
    btn.onclick = null;
    document.getElementById('formTurno').onsubmit = handleTurnoSubmit;
    editandoId = null; editandoTipo = null;
}

function mostrarToast(msg, tipo = 'success') {
    const toast = document.getElementById('toast');
    document.getElementById('toastMessage').textContent = msg;
    toast.className = `toast ${tipo === 'error' ? 'error' : ''}`;
    toast.classList.remove('hidden');
    setTimeout(() => toast.classList.add('show'), 100);
    setTimeout(() => {
        toast.classList.remove('show');
        setTimeout(() => toast.classList.add('hidden'), 400);
    }, 3000);
}

function cerrarToast() {
    const toast = document.getElementById('toast');
    toast.classList.remove('show');
    setTimeout(() => toast.classList.add('hidden'), 400);
}
