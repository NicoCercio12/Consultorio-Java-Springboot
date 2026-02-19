const API_URL = "http://localhost:8080";

// ================= TURNOS =================
function cargarTurnos() {
    fetch(`${API_URL}/turnos`)
        .then(res => res.json())
        .then(data => {
            const lista = document.getElementById("listaTurnos");
            lista.innerHTML = "";

            data.forEach(t => {
                const li = document.createElement("li");
                li.className = "list-group-item";
                li.textContent = `Turno #${t.idTurno} | Médico ID: ${t.idMedico} | Paciente ID: ${t.idPaciente} | ${t.fechaHora}`;
                lista.appendChild(li);
            });
        });
}

// ================= PACIENTES =================
function cargarPacientes() {
    fetch(`${API_URL}/pacientes`)
        .then(res => res.json())
        .then(data => {
            const lista = document.getElementById("listaPacientes");
            lista.innerHTML = "";

            data.forEach(p => {
                const li = document.createElement("li");
                li.className = "list-group-item";
                li.textContent = `${p.nombre} ${p.apellido} - DNI ${p.dni}`;
                lista.appendChild(li);
            });
        });
}

// ================= MEDICOS =================
function cargarMedicos() {
    fetch(`${API_URL}/medicos`)
        .then(res => res.json())
        .then(data => {
            const lista = document.getElementById("listaMedicos");
            lista.innerHTML = "";

            data.forEach(m => {
                const li = document.createElement("li");
                li.className = "list-group-item";
                li.textContent = `${m.nombre} ${m.apellido} - ${m.especialidad}`;
                lista.appendChild(li);
            });
        });
}
