async function handleSelectChangeResponse(event) {
    const selectedTitle = event.target.value;
    findTicket(selectedTitle)
}

async function handleSelectChangeResponseStatus(event) {
    const selectedTitle = event.target.value;
    setStatus(selectedTitle)
}
async function findTicket(title) {
    const response = await fetch("/ticket/title/" + title);
    let select = document.getElementById("ticket__add__response");
    select.innerHTML = '';
    data = await response.json();
    select.name = 'ticket';
    select.style.display = 'block';
    const defaultOption = document.createElement('option');
    defaultOption.value = '-1';
    defaultOption.textContent = 'Select The Relevant Ticket:';
    defaultOption.disabled = true;
    defaultOption.selected = true;
    select.appendChild(defaultOption);
    data.forEach(function (item) {
        let opt = document.createElement('option');
        opt.value = item.id;
        opt.innerHTML = item.title + " :\n " + item.request;
        select.appendChild(opt);
    });


}

function addResponse() {
    let saveModal = document.getElementById("saveModalResponse");
    saveModal.style.display = "block"
}

async function findId(id) {
    let editModal = document.getElementById("editModalResponse")
    editModal.style.display = 'block';
    const resp = await fetch("/response/" + id, {
        method: "get"
    });
    const data = await resp.json();
    let responseId = document.getElementById("id__edit__response");
    let responseDate = document.getElementById("responseTimeStamp__edit__response");
    let responseGroup = document.getElementById("responseGroup__edit__response");
    // let responseResponder = document.getElementById("applicant__edit__response");
    let ticketResponse = document.getElementById("ticket__edit__response");
    let ticketIdResponse = document.getElementById("ticketId__edit__response");
    let ticketStatusResponse = document.getElementById("ticketStatus__edit__response");
    let responseTicket = document.getElementById("ticketResponse__edit__response");
    let responseDeleted = document.getElementById("deleted__edit__response");


    responseId.value = data.id;
    responseDate.value = new Date(data.responseTimeStamp).toISOString().slice(0, 16);
    responseGroup.value = data.ticket.group.title;
    // responseResponder.value = data.responder.username;
    ticketResponse.value = data.ticket.request;
    ticketIdResponse.value = data.ticket.id;
    ticketStatusResponse.value = data.ticket.status;
    responseTicket.value = data.ticketResponse;
    responseDeleted.value = data.deleted;

}

async function edit() {
    let editModal = document.getElementById("editModalResponse")
    editModal.style.display = 'none';
    const formData = new FormData(document.getElementById("editFormResponse"));

    if (confirm("از صحت اطلاعات وارد شده اطمینان دارید؟")) {
        const response = await fetch("/response", {method: "put", body: formData});
        if (!response.ok) {
            showErrorPopup('/response', response.status, (await response.text()).toString());
        } else {
            showInfoPopup('/response', response.status, (await response.text()).toString());
        }
    }

}

async function save() {
    let saveModal = document.getElementById("saveModalResponse")
    saveModal.style.display = 'none'
    const saveFormData = new FormData(saveFormResponse);
    const editTicketForm = new FormData(saveFormResponse);
    if (confirm("از صحت اطلاعات وارد شده اطمینان دارید؟")) {
        const response = await fetch("/response", {method: "post", body: saveFormData});
        // const ticketEdit = await fetch('/ticket' , {method  : 'put' , body : editTicketForm })
        if (!response.ok) {
            showErrorPopup('/response', response.status, (await response.text()).toString());
        } else {
            showInfoPopup('/response', response.status, (await response.text()).toString());
        }
    }

}

async function deleted(id) {
    if (confirm("آیا از حذف تیکت " + id + " اطمینان دارید؟")) {
        const response = await fetch("/response/" + id, {
            method: "delete"
        });
        if (!response.ok) {
            showErrorPopup('/response', response.status, (await response.text()).toString());
        } else {
            showInfoPopup('/response', response.status, (await response.text()).toString());
        }
    }

}

function closeModal() {
    let saveModal = document.getElementById("saveModalResponse")
    let editModal = document.getElementById("editModalResponse")
    editModal.style.display = 'none';
    saveModal.style.display = "none"
}

async function setStatus(id) {
    const response = await fetch('/ticket/' + id, {
        method: "post"
    });

}