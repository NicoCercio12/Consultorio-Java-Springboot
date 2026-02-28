const API_BASE = 'http://localhost:8080';

// 🔒 ANTI-LOOP FLAGS
let isLoadingMedicos = false;
let isLoadingPacientes = false;
let isLoadingTurnos = false;

// Navegación directa
function mostrarSeccion(seccion) {
    // Ocultar todas las secciones
    document.querySelectorAll('.section').forEach(s => {
        s.classList.remove('active');
        s.classList.add('hidden');
    });
    
    // Mostrar sección seleccionada
    document.getElementById(seccion).classList.remove('hidden');
    document.getElementById(seccion).classList.add('active');
    
    // Actualizar botones activos
    document.querySelectorAll('.nav-btn').forEach(btn => btn.classList.remove('active'));
    event.target.classList.add('active');
    
    // Cargar datos SOLO UNA VEZ
    if (seccion === 'medicos') cargarMedicos();
    else if (seccion === 'pacientes') cargarPacientes();
    else cargarTurnos();
}

// Inicialización
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('formMedico').addEventListener('submit', handleMedicoSubmit);
    document.getElementById('formPaciente').addEventListener('submit', handlePacienteSubmit);
    document.getElementById('formTurno').addEventListener('submit', handleTurnoSubmit);
    cargarMedicos(); // Carga inicial
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
        console.log('📤 POST /medicos:', dto);
        const response = await fetch(`${API_BASE}/medicos`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dto)
        });
        
        console.log('📥 Status:', response.status, response.ok);
        
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(`HTTP ${response.status}: ${errorText}`);
        }
        
        console.log('✅ Médico guardado');
        mostrarToast('✅ Médico agregado correctamente');
        limpiarForm('medico');
        await cargarMedicos(); // Recarga lista
        
    } catch (error) {
        console.error('❌ ERROR:', error);
        mostrarToast('❌ ' + error.message, 'error');
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
        console.log('📤 POST /pacientes:', dto);
        const response = await fetch(`${API_BASE}/pacientes`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dto)
        });
        
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(`HTTP ${response.status}: ${errorText}`);
        }
        
        mostrarToast('✅ Paciente agregado correctamente');
        limpiarForm('paciente');
        await cargarPacientes();
        
    } catch (error) {
        console.error('❌ ERROR:', error);
        mostrarToast('❌ ' + error.message, 'error');
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
        console.log('📤 POST /turnos:', dto);
        const response = await fetch(`${API_BASE}/turnos`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dto)
        });
        
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(`HTTP ${response.status}: ${errorText}`);
        }
        
        mostrarToast('✅ Turno creado correctamente');
        limpiarForm('turno');
        await cargarTurnos();
        
    } catch (error) {
        console.error('❌ ERROR:', error);
        mostrarToast('❌ ' + error.message, 'error');
    }
}

// 🔒 CARGAR CON ANTI-LOOP
async function cargarMedicos() {
    if (isLoadingMedicos) return;
    isLoadingMedicos = true;
    
    try {
        const res = await fetch(`${API_BASE}/medicos`);
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        const medicos = await res.json();
        renderLista('listaMedicos', medicos, 'medico');
    } catch (error) {
        console.error('❌ Médicos:', error);
        document.getElementById('listaMedicos').innerHTML = 
            '<p class="text-center text-red-500 py-8">Error cargando médicos</p>';
    } finally {
        isLoadingMedicos = false;
    }
}

async function cargarPacientes() {
    if (isLoadingPacientes) return;
    isLoadingPacientes = true;
    
    try {
        const res = await fetch(`${API_BASE}/pacientes`);
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        const pacientes = await res.json();
        renderLista('listaPacientes', pacientes, 'paciente');
    } catch (error) {
        console.error('❌ Pacientes:', error);
        document.getElementById('listaPacientes').innerHTML = 
            '<p class="text-center text-red-500 py-8">Error cargando pacientes</p>';
    } finally {
        isLoadingPacientes = false;
    }
}

async function cargarTurnos() {
    if (isLoadingTurnos) return;
    isLoadingTurnos = true;
    
    try {
        const res = await fetch(`${API_BASE}/turnos`);
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        const turnos = await res.json();
        renderLista('listaTurnos', turnos, 'turno');
    } catch (error) {
        console.error('❌ Turnos:', error);
        document.getElementById('listaTurnos').innerHTML = 
            '<p class="text-center text-red-500 py-8">Error cargando turnos</p>';
    } finally {
        isLoadingTurnos = false;
    }
}

function renderLista(containerId, items, tipo) {
    const container = document.getElementById(containerId);
    
    if (!items || items.length === 0) {
        container.innerHTML = `<p class="text-center text-gray-500 py-8">No hay ${tipo}s registrados</p>`;
        return;
    }
    
    container.innerHTML = items.map(item => {
        const id = item.id || item.idPersona || item.idTurno;
        const detalles = Object.entries(item)
            .filter(([key]) => !['id', 'idPersona', 'idTurno'].includes(key))
            .map(([key, value]) => `${key.charAt(0).toUpperCase() + key.slice(1)}: ${value || 'N/A'}`)
            .join(' | ');
            
        return `
            <div class="list-item">
                <div class="item-content">
                    <h4>${item.nombre || `ID: ${id}`}</h4>
                    <p>${detalles}</p>
                </div>
                <div class="item-actions">
                    <button class="btn-action btn-edit" onclick="editar(${id}, '${tipo}')" title="Editar">
                        <i class="fas fa-edit"></i>
                    </button>
                    <button class="btn-action btn-delete" onclick="eliminar(${id}, '${tipo}')" title="Eliminar">
                        <i class="fas fa-trash"></i>
                    </button>
                </div>
            </div>
        `;
    }).join('');
}

// CRUD
async function eliminar(id, tipo) {
    if (confirm(`¿Eliminar este ${tipo}?`)) {
        try {
            await fetch(`${API_BASE}/${tipo}s/${id}`, { method: 'DELETE' });
            mostrarToast(`✅ ${tipo.charAt(0).toUpperCase() + tipo.slice(1)} eliminado`);
            
            // Recarga sección activa
            if (document.getElementById('medicos').classList.contains('active')) 
                cargarMedicos();
            else if (document.getElementById('pacientes').classList.contains('active')) 
                cargarPacientes();
            else cargarTurnos();
        } catch (error) {
            mostrarToast('❌ Error al eliminar', 'error');
        }
    }
}

function editar(id, tipo) {
    alert(`✏️ Editar ${tipo} ID: ${id}\n\nPróximamente...`);
}

function limpiarForm(prefix) {
    document.querySelectorAll(`#form${prefix.charAt(0).toUpperCase() + prefix.slice(1)} input`)
        .forEach(input => input.value = '');
}

function mostrarToast(mensaje, tipo = 'success') {
    const toast = document.getElementById('toast');
    document.getElementById('toastMessage').textContent = mensaje;
    toast.className = `toast ${tipo === 'error' ? 'error' : ''}`;
    toast.classList.remove('hidden');
    setTimeout(() => toast.classList.add('show'), 100);
    
    setTimeout(() => {
        toast.classList.remove('show');
        setTimeout(() => toast.classList.add('hidden'), 400);
    }, 4000);
}

function cerrarToast() {
    const toast = document.getElementById('toast');
    toast.classList.remove('show');
    setTimeout(() => toast.classList.add('hidden'), 400);
}
