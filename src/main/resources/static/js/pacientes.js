document.getElementById("pacienteForm").addEventListener("submit", async e => {
    e.preventDefault();

    const paciente = {
        nombre: document.getElementById("nombre").value,
        apellido: document.getElementById("apellido").value,
        dni: document.getElementById("dni").value
    };

    const response = await fetch("/pacientes", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(paciente)
    });

    const texto = await response.text();
    document.getElementById("resultado").innerText = texto;
});
