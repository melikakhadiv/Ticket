function addTicketGroup() {
    let saveModal = document.getElementById("saveModalTicketGroup");
    saveModal.style.display = "block"
}

async function findId(id) {
    let editModal = document.getElementById("editModalTicketGroup")
    editModal.style.display = 'block';
    const resp = await fetch("/ticketGroup/" + id, {
        method: "get"
    });
    const data = await resp.json();
    console.log(data)
    let ticketId = document.getElementById("id__edit__ticketGroup");
    let ticketParent = document.getElementById("parent__edit__ticketGroup");
    let ticketTitle = document.getElementById("title__edit__ticketGroup");


    ticketId.value = data.id;
    ticketTitle.value = data.title;
    if (data.parent) {
        ticketParent.value = data.parent.id;
    } else {
        ticketParent.value = "";
    }

}

async function edit() {

    const editModal = document.getElementById("editModalTicketGroup");
    editModal.style.display = 'none';

    const formData = new FormData(document.getElementById("editFormTicketGroup"));
    console.log(formData)

    if (confirm("از صحت اطلاعات وارد شده اطمینان دارید؟")) {
        const response = await fetch("/ticketGroup", {method: "put", body: formData});
        if (!response.ok) {
            showErrorPopup('/ticketGroup', response.status, (await response.text()).toString());
        } else {
            showInfoPopup('/ticketGroup', response.status, (await response.text()).toString());
        }
    }

}

async function save() {
    let saveModal = document.getElementById("saveModalTicketGroup")
    saveModal.style.display = 'none';

    const saveFormData = new FormData();
    let ticketGroupTitle = document.getElementById("title__add__ticketGroup");
    let ticketGroupParent = document.getElementById("parent__add__ticketGroup");

    saveFormData.append('title', ticketGroupTitle.value)
    if (ticketGroupParent != null) {
        saveFormData.append('parent', ticketGroupParent.value)
    }

    // todo : hide modal after click
    if (confirm("از صحت اطلاعات وارد شده اطمینان دارید؟")) {
        const response = await fetch("/ticketGroup", {method: "post", body: saveFormData});
        if (!response.ok) {
            showErrorPopup('/ticketGroup', response.status, (await response.text()).toString());
        } else {
            showInfoPopup('/ticketGroup', response.status, (await response.text()).toString());
        }
    }


}

async function deleted(id) {
    //todo : cascade all? - melika
    if (confirm("آیا از حذف تیکت " + id + " اطمینان دارید؟")) {
        const response = await fetch("/ticketGroup/" + id, {
            method: "delete"
        });

        if (!response.ok) {
            showErrorPopup('/ticketGroup', response.status, (await response.text()).toString());
        } else {
            showInfoPopup('/ticketGroup', response.status, (await response.text()).toString());
        }

    }
}

function closeModal() {
    let saveModal = document.getElementById("saveModalTicketGroup")
    let editModal = document.getElementById("editModalTicketGroup")
    editModal.style.display = 'none';
    saveModal.style.display = "none"
}