var API_CATALOG = {
    Doctor: [
        { label: "Test", method: "GET", path: "/carepulse/doctors/test" },
        { label: "Get All", method: "GET", path: "/carepulse/doctors" },
        { label: "Get Active", method: "GET", path: "/carepulse/doctors/active" },
        { label: "Get By ID", method: "GET", path: "/carepulse/doctors/{id}" },
        { label: "Create", method: "POST", path: "/carepulse/doctors", bodyTemplate: { firstName: "", lastName: "", email: "", phone: "", specializationId: 0, doctorRegistrationNo: "", sex: "MALE", createdAt: "2026-01-01T10:00:00" } },
        { label: "Update", method: "PUT", path: "/carepulse/doctors/{id}", bodyTemplate: { firstName: "", lastName: "", email: "", phone: "", specializationId: 0, doctorRegistrationNo: "", sex: "MALE", createdAt: "2026-01-01T10:00:00" } },
        { label: "Delete", method: "DELETE", path: "/carepulse/doctors/{id}" }
    ],
    Patient: [
        { label: "Test", method: "GET", path: "/carepulse/patients/test" },
        { label: "Get All", method: "GET", path: "/carepulse/patients" },
        { label: "Get Active", method: "GET", path: "/carepulse/patients/active" },
        { label: "Get By ID", method: "GET", path: "/carepulse/patients/{id}" },
        { label: "Create", method: "POST", path: "/carepulse/patients", bodyTemplate: { firstName: "", middleName: "", lastName: "", sex: "MALE", email: "", phone: "", address: "", status: "ACTIVE" } },
        { label: "Update", method: "PUT", path: "/carepulse/patients/{id}", bodyTemplate: { firstName: "", middleName: "", lastName: "", sex: "MALE", email: "", phone: "", address: "", status: "ACTIVE" } },
        { label: "Delete", method: "DELETE", path: "/carepulse/patients/{id}" }
    ],
    Hospital: [
        { label: "Test", method: "GET", path: "/carepulse/hospital/message" },
        { label: "Get All", method: "GET", path: "/carepulse/hospital" },
        { label: "Get Active", method: "GET", path: "/carepulse/hospital/active" },
        { label: "Get By ID", method: "GET", path: "/carepulse/hospital/{id}" },
        { label: "Create", method: "POST", path: "/carepulse/hospital", bodyTemplate: { hospitalId: null, hospitalCode: "", hospitalName: "", hospitalEmail: "", hospitalPhone: "", hospitalAddress: "", status: "ACTIVE", createdAt: "2026-01-01T10:00:00", updatedAt: "2026-01-01T10:00:00" } },
        { label: "Update", method: "PUT", path: "/carepulse/hospital/{id}", bodyTemplate: { hospitalId: null, hospitalCode: "", hospitalName: "", hospitalEmail: "", hospitalPhone: "", hospitalAddress: "", status: "ACTIVE", createdAt: "2026-01-01T10:00:00", updatedAt: "2026-01-01T10:00:00" } },
        { label: "Delete", method: "DELETE", path: "/carepulse/hospital/{id}" }
    ],
    HospitalDoctor: [
        { label: "Get All", method: "GET", path: "/carepulse/hospital-doctor" },
        { label: "Get Active", method: "GET", path: "/carepulse/hospital-doctor/active" },
        { label: "By Hospital", method: "GET", path: "/carepulse/hospital-doctor/hospital/{hospitalId}" },
        { label: "Active By Hospital", method: "GET", path: "/carepulse/hospital-doctor/active/hospital/{hospitalId}" },
        { label: "By Doctor", method: "GET", path: "/carepulse/hospital-doctor/doctor/{doctorId}" },
        { label: "Active By Doctor", method: "GET", path: "/carepulse/hospital-doctor/active/doctor/{doctorId}" },
        { label: "Create", method: "POST", path: "/carepulse/hospital-doctor", bodyTemplate: { hospitalId: 0, doctorId: 0 } }
    ],
    HospitalPatient: [
        { label: "Test", method: "GET", path: "/carepulse/hospital-patient/test" },
        { label: "Get All", method: "GET", path: "/carepulse/hospital-patient" },
        { label: "By Hospital", method: "GET", path: "/carepulse/hospital-patient/hospital/{hospitalId}" },
        { label: "By Patient", method: "GET", path: "/carepulse/hospital-patient/patient/{patientId}" },
        { label: "Create", method: "POST", path: "/carepulse/hospital-patient", bodyTemplate: { hospitalId: 0, patientId: 0, hospitalPatientNo: "" } }
    ],
    FollowUp: [
        { label: "Test", method: "GET", path: "/carepulse/followup/test" },
        { label: "Get All", method: "GET", path: "/carepulse/followup" },
        { label: "Get By ID", method: "GET", path: "/carepulse/followup/{id}" },
        { label: "By Patient Case", method: "GET", path: "/carepulse/followup/patientcase/{patientCaseId}" },
        { label: "By Doctor", method: "GET", path: "/carepulse/followup/doctor/{doctorId}" },
        { label: "By Patient", method: "GET", path: "/carepulse/followup/patient/{patientId}" },
        { label: "By Hospital", method: "GET", path: "/carepulse/followup/hospital/{hospitalId}" },
        { label: "Create", method: "POST", path: "/carepulse/followup", bodyTemplate: { followUpId: null, patientCaseId: 0, followUpDate: "2026-01-01", followUpTime: "10:30:00", purpose: "", followUpStatus: "SCHEDULED", notes: "", createdAt: "2026-01-01T10:00:00", updatedAt: "2026-01-01T10:00:00" } },
        { label: "Update", method: "PUT", path: "/carepulse/followup/{id}", bodyTemplate: { followUpId: null, patientCaseId: 0, followUpDate: "2026-01-01", followUpTime: "10:30:00", purpose: "", followUpStatus: "SCHEDULED", notes: "", createdAt: "2026-01-01T10:00:00", updatedAt: "2026-01-01T10:00:00" } }
    ],
    Medicine: [
        { label: "Test", method: "GET", path: "/carepulse/medicine/message" },
        { label: "Get All", method: "GET", path: "/carepulse/medicine" },
        { label: "Get Active", method: "GET", path: "/carepulse/medicine/active" },
        { label: "Get By ID", method: "GET", path: "/carepulse/medicine/{id}" },
        { label: "By Name", method: "GET", path: "/carepulse/medicine/name/{name}" },
        { label: "By Brand", method: "GET", path: "/carepulse/medicine/brand/{brand}" },
        { label: "Create", method: "POST", path: "/carepulse/medicine", bodyTemplate: { medicineName: "", brandName: "", form: "TABLET", strength: "500mg", description: "", status: "ACTIVE", createdAt: "2026-01-01T10:00:00", updatedAt: "2026-01-01T10:00:00" } },
        { label: "Update", method: "PUT", path: "/carepulse/medicine/{id}", bodyTemplate: { medicineName: "", brandName: "", form: "TABLET", strength: "500mg", description: "", status: "ACTIVE", createdAt: "2026-01-01T10:00:00", updatedAt: "2026-01-01T10:00:00" } },
        { label: "Delete", method: "DELETE", path: "/carepulse/medicine/{id}" }
    ],
    Medication: [
        { label: "Test", method: "GET", path: "/carepulse/medication/message" },
        { label: "Get All", method: "GET", path: "/carepulse/medication" },
        { label: "Get By ID", method: "GET", path: "/carepulse/medication/{medicationId}" },
        { label: "By Patient Case", method: "GET", path: "/carepulse/medication/patientCase/{patientCaseId}" },
        { label: "By Doctor", method: "GET", path: "/carepulse/medication/doctor/{doctorId}" },
        { label: "Create", method: "POST", path: "/carepulse/medication", bodyTemplate: { patientCaseId: 0, medicineId: 0, doctorId: 0, dosage: "", frequency: "", route: "ORAL", startDate: "2026-01-01", endDate: "2026-01-07", instructions: "", status: "ACTIVE", createdAt: "2026-01-01T10:00:00", updatedAt: "2026-01-01T10:00:00" } },
        { label: "Update", method: "PUT", path: "/carepulse/medication/{medicationId}", bodyTemplate: { patientCaseId: 0, medicineId: 0, doctorId: 0, dosage: "", frequency: "", route: "ORAL", startDate: "2026-01-01", endDate: "2026-01-07", instructions: "", status: "ACTIVE", createdAt: "2026-01-01T10:00:00", updatedAt: "2026-01-01T10:00:00" } }
    ],
    MedicineTake: [
        { label: "Test", method: "GET", path: "/carepulse/medicine-take/message" },
        { label: "Get All", method: "GET", path: "/carepulse/medicine-take" },
        { label: "Get By ID", method: "GET", path: "/carepulse/medicine-take/{id}" },
        { label: "By Reminder", method: "GET", path: "/carepulse/medicine-take/reminder/{reminderId}" },
        { label: "By Patient", method: "GET", path: "/carepulse/medicine-take/patient/{patientId}" },
        { label: "By Doctor", method: "GET", path: "/carepulse/medicine-take/doctor/{doctorId}" },
        { label: "By Status", method: "GET", path: "/carepulse/medicine-take/status/{status}" },
        { label: "Create", method: "POST", path: "/carepulse/medicine-take", bodyTemplate: { id: null, medicationId: 0, reminderId: 0, scheduledTime: "2026-01-01T10:00:00", actionTime: "2026-01-01T10:30:00", status: "TAKEN", notes: "", createdAt: "2026-01-01T10:00:00", updatedAt: "2026-01-01T10:00:00" } },
        { label: "Update", method: "PUT", path: "/carepulse/medicine-take/{id}", bodyTemplate: { id: null, medicationId: 0, reminderId: 0, scheduledTime: "2026-01-01T10:00:00", actionTime: "2026-01-01T10:30:00", status: "TAKEN", notes: "", createdAt: "2026-01-01T10:00:00", updatedAt: "2026-01-01T10:00:00" } }
    ],
    Reminder: [
        { label: "Test", method: "GET", path: "/carepulse/reminder/test" },
        { label: "Get All", method: "GET", path: "/carepulse/reminder" },
        { label: "Get By ID", method: "GET", path: "/carepulse/reminder/{reminderId}" },
        { label: "By Patient", method: "GET", path: "/carepulse/reminder/patient/{patientId}" },
        { label: "By Doctor", method: "GET", path: "/carepulse/reminder/doctor/{doctorId}" },
        { label: "Create", method: "POST", path: "/carepulse/reminder", bodyTemplate: { reminderId: null, medicationId: 0, reminderTime: "2026-01-01T08:00:00", reminderType: "PUSH", repeatType: "DAILY", repeatDays: "MON,TUE", reminderMessage: "", status: "ACTIVE", createdAt: "2026-01-01T10:00:00", updatedAt: "2026-01-01T10:00:00" } },
        { label: "Update", method: "PUT", path: "/carepulse/reminder/{reminderid}", bodyTemplate: { reminderId: null, medicationId: 0, reminderTime: "2026-01-01T08:00:00", reminderType: "PUSH", repeatType: "DAILY", repeatDays: "MON,TUE", reminderMessage: "", status: "ACTIVE", createdAt: "2026-01-01T10:00:00", updatedAt: "2026-01-01T10:00:00" } }
    ],
    PatientCase: [
        { label: "Test", method: "GET", path: "/carepulse/patient-case/test" },
        { label: "Get All", method: "GET", path: "/carepulse/patient-case" },
        { label: "Get By ID", method: "GET", path: "/carepulse/patient-case/{id}" },
        { label: "By Patient", method: "GET", path: "/carepulse/patient-case/patient/{patientId}" },
        { label: "By Doctor", method: "GET", path: "/carepulse/patient-case/doctor/{doctorId}" },
        { label: "By Hospital", method: "GET", path: "/carepulse/patient-case/hospital/{hospitalId}" },
        { label: "Create", method: "POST", path: "/carepulse/patient-case", bodyTemplate: { hospitalId: 0, patientId: 0, doctorId: 0, caseTitle: "", diagnosis: "", admissionDate: "2026-01-01", dischargeDate: "2026-01-05", note: "" } }
    ],
    Specialization: [
        { label: "Test", method: "GET", path: "/carepulse/specializations/message" },
        { label: "Get All", method: "GET", path: "/carepulse/specializations" },
        { label: "Get Active", method: "GET", path: "/carepulse/specializations/active" },
        { label: "Get By ID", method: "GET", path: "/carepulse/specializations/{id}" },
        { label: "Create", method: "POST", path: "/carepulse/specializations", bodyTemplate: { specializationId: null, specializationCode: "", specializationName: "", specializationDescription: "", status: "ACTIVE" } },
        { label: "Update", method: "PUT", path: "/carepulse/specializations/{id}", bodyTemplate: { specializationId: null, specializationCode: "", specializationName: "", specializationDescription: "", status: "ACTIVE" } },
        { label: "Delete", method: "DELETE", path: "/carepulse/specializations/{id}" }
    ]
};

var state = {
    currentResource: null,
    currentOperationIndex: 0,
    lastJson: null
};

function getPathParams(path) {
    var matches = path.match(/\{[^}]+\}/g);
    if (!matches) {
        return [];
    }
    var params = [];
    for (var i = 0; i < matches.length; i++) {
        params.push(matches[i].substring(1, matches[i].length - 1));
    }
    return params;
}

function prettifyJson(value) {
    return JSON.stringify(value, null, 2);
}

function setViewMode(isJsonMode) {
    var jsonOutput = document.getElementById("jsonOutput");
    var tableOutput = document.getElementById("tableOutput");
    var jsonBtn = document.getElementById("jsonViewBtn");
    var tableBtn = document.getElementById("tableViewBtn");

    if (isJsonMode) {
        jsonOutput.classList.remove("hidden");
        tableOutput.classList.add("hidden");
        jsonBtn.classList.add("active");
        tableBtn.classList.remove("active");
    } else {
        jsonOutput.classList.add("hidden");
        tableOutput.classList.remove("hidden");
        jsonBtn.classList.remove("active");
        tableBtn.classList.add("active");
    }
}

function renderTable(data) {
    var tableOutput = document.getElementById("tableOutput");

    var rows = [];
    if (Array.isArray(data)) {
        rows = data;
    } else if (data && typeof data === "object") {
        rows = [data];
    }

    if (rows.length === 0 || typeof rows[0] !== "object" || rows[0] === null) {
        tableOutput.innerHTML = "<div class='empty-note'>Table view is available for object/array JSON responses only.</div>";
        return;
    }

    var columnsMap = {};
    for (var i = 0; i < rows.length; i++) {
        var keys = Object.keys(rows[i]);
        for (var j = 0; j < keys.length; j++) {
            columnsMap[keys[j]] = true;
        }
    }
    var columns = Object.keys(columnsMap);

    var html = "<table><thead><tr>";
    for (var c = 0; c < columns.length; c++) {
        html += "<th>" + columns[c] + "</th>";
    }
    html += "</tr></thead><tbody>";

    for (var r = 0; r < rows.length; r++) {
        html += "<tr>";
        for (var k = 0; k < columns.length; k++) {
            var val = rows[r][columns[k]];
            if (val === null || val === undefined) {
                html += "<td></td>";
            } else if (typeof val === "object") {
                html += "<td>" + prettifyJson(val).replace(/</g, "&lt;") + "</td>";
            } else {
                html += "<td>" + String(val).replace(/</g, "&lt;") + "</td>";
            }
        }
        html += "</tr>";
    }

    html += "</tbody></table>";
    tableOutput.innerHTML = html;
}

function buildPathParams(path) {
    var container = document.getElementById("pathParamsContainer");
    container.innerHTML = "";

    var params = getPathParams(path);
    if (params.length === 0) {
        return;
    }

    for (var i = 0; i < params.length; i++) {
        var row = document.createElement("div");
        row.className = "path-param-row";

        var label = document.createElement("label");
        label.textContent = "Path param: " + params[i];

        var input = document.createElement("input");
        input.type = "text";
        input.id = "pathParam_" + params[i];
        input.placeholder = "Enter " + params[i];

        row.appendChild(label);
        row.appendChild(input);
        container.appendChild(row);
    }
}

function updateOperationUi() {
    var operations = API_CATALOG[state.currentResource];
    var operation = operations[state.currentOperationIndex];
    var methodPath = operation.method + " " + operation.path;

    document.getElementById("operationMeta").textContent = methodPath;

    buildPathParams(operation.path);

    var bodyContainer = document.getElementById("bodyContainer");
    var bodyField = document.getElementById("requestBody");
    if (operation.bodyTemplate) {
        bodyContainer.classList.remove("hidden");
        bodyField.value = prettifyJson(operation.bodyTemplate);
    } else {
        bodyContainer.classList.add("hidden");
        bodyField.value = "";
    }
}

function refreshOperationSelect() {
    var operations = API_CATALOG[state.currentResource];
    var select = document.getElementById("operationSelect");
    select.innerHTML = "";

    for (var i = 0; i < operations.length; i++) {
        var option = document.createElement("option");
        option.value = String(i);
        option.textContent = operations[i].label + " (" + operations[i].method + ")";
        select.appendChild(option);
    }

    state.currentOperationIndex = 0;
    select.value = "0";
    updateOperationUi();
}

function buildFinalPath(operation) {
    var finalPath = operation.path;
    var params = getPathParams(operation.path);
    for (var i = 0; i < params.length; i++) {
        var id = "pathParam_" + params[i];
        var value = document.getElementById(id).value;
        if (!value || value.trim().length === 0) {
            throw new Error("Missing required path parameter: " + params[i]);
        }
        finalPath = finalPath.replace("{" + params[i] + "}", encodeURIComponent(value.trim()));
    }
    return finalPath;
}

function setResponseSummary(message, isError) {
    var summary = document.getElementById("responseSummary");
    summary.textContent = message;
    if (isError) {
        summary.style.background = "#fee2e2";
        summary.style.borderColor = "#fecaca";
        summary.style.color = "#b91c1c";
    } else {
        summary.style.background = "#f8fafc";
        summary.style.borderColor = "#e2e8f0";
        summary.style.color = "#334155";
    }
}

async function executeCurrentOperation() {
    var operations = API_CATALOG[state.currentResource];
    var operation = operations[state.currentOperationIndex];
    var baseUrl = document.getElementById("baseUrl").value.trim();

    var resolvedPath = buildFinalPath(operation);
    var fullUrl = baseUrl + resolvedPath;

    var options = {
        method: operation.method,
        headers: {
            Accept: "application/json"
        }
    };

    if (operation.bodyTemplate) {
        var rawBody = document.getElementById("requestBody").value.trim();
        if (rawBody.length > 0) {
            try {
                JSON.parse(rawBody);
            } catch (err) {
                throw new Error("Request body must be valid JSON.");
            }
            options.headers["Content-Type"] = "application/json";
            options.body = rawBody;
        }
    }

    var start = performance.now();
    var response = await fetch(fullUrl, options);
    var duration = Math.round(performance.now() - start);

    var text = await response.text();
    var parsed = null;
    if (text) {
        try {
            parsed = JSON.parse(text);
        } catch (e) {
            parsed = text;
        }
    }

    state.lastJson = parsed;

    var statusLine = operation.method + " " + resolvedPath + " | Status: " + response.status + " " + response.statusText + " | " + duration + " ms";
    setResponseSummary(statusLine, !response.ok);

    var jsonOutput = document.getElementById("jsonOutput");
    if (parsed === null) {
        jsonOutput.textContent = "<empty response>";
    } else if (typeof parsed === "string") {
        jsonOutput.textContent = parsed;
    } else {
        jsonOutput.textContent = prettifyJson(parsed);
    }

    renderTable(parsed);
}

function initExplorer() {
    var baseUrl = document.getElementById("baseUrl");
    baseUrl.value = window.location.origin;

    var resourceSelect = document.getElementById("resourceSelect");
    var resources = Object.keys(API_CATALOG);

    for (var i = 0; i < resources.length; i++) {
        var option = document.createElement("option");
        option.value = resources[i];
        option.textContent = resources[i];
        resourceSelect.appendChild(option);
    }

    state.currentResource = resources[0];
    resourceSelect.value = resources[0];
    refreshOperationSelect();

    resourceSelect.addEventListener("change", function () {
        state.currentResource = resourceSelect.value;
        refreshOperationSelect();
    });

    document.getElementById("operationSelect").addEventListener("change", function (e) {
        state.currentOperationIndex = Number(e.target.value);
        updateOperationUi();
    });

    document.getElementById("executeBtn").addEventListener("click", async function () {
        try {
            await executeCurrentOperation();
        } catch (err) {
            setResponseSummary(String(err.message || err), true);
            document.getElementById("jsonOutput").textContent = String(err.message || err);
            document.getElementById("tableOutput").innerHTML = "";
        }
    });

    document.getElementById("resetBtn").addEventListener("click", function () {
        refreshOperationSelect();
        setResponseSummary("Inputs reset for selected resource.", false);
        document.getElementById("jsonOutput").textContent = "Run a request to see API output.";
        document.getElementById("tableOutput").innerHTML = "";
        setViewMode(true);
    });

    document.getElementById("jsonViewBtn").addEventListener("click", function () {
        setViewMode(true);
    });

    document.getElementById("tableViewBtn").addEventListener("click", function () {
        setViewMode(false);
    });
}

initExplorer();
