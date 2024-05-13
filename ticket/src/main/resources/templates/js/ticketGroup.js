async function findId(id) {
    let editModal = document.getElementById("editModalTicketGroup")
    let modalTicketGroup = new bootstrap.Modal(editModal);
    modalTicketGroup.show();
    const resp = await fetch("/ticketGroup/" + id, {
        method: "get"
    });
    const data = await resp.json();
    let ticketId = document.getElementById("id__edit__ticketGroup");
    let ticketParent = document.getElementById("parent__edit__ticketGroup");
    let ticketTitle = document.getElementById("title__edit__ticketGroup");


    ticketId.value = data.id;
    ticketTitle.value = data.title;
    if (data.parent) {
        ticketParent.value = data.parent.id;
    } else {
        ticketParent.value = ""; // Reset the dropdown if there's no parent
    }

}

async function edit() {
    // todo : close modal then pop up
    const editModal = document.getElementById("editModalTicketGroup");
    const modal = new bootstrap.Modal(editModal);
    modal.hide();

    const formData = new FormData(document.getElementById("editFormTicketGroup"));
    console.log(formData)

    if (confirm("از صحت اطلاعات وارد شده اطمینان دارید؟")) {
        const response = await fetch("/ticketGroup", {method: "put", body: formData});
        if(!response.ok){
            showErrorPopup('/ticketGroup',response.status ,(await response.text()).toString());
        }else {
            showInfoPopup('/ticketGroup', response.status , (await response.text()).toString());
        }
    }

}

async function save() {
    // todo : close modal then pop up
    let saveModal = document.getElementById("saveModalTicketGroup")
    let modalTicketGroup = new bootstrap.Modal(saveModal);
    modalTicketGroup.hide();

    const saveFormData = new FormData();
    let ticketGroupTitle = document.getElementById("title__add__ticketGroup");
    let ticketGroupParent = document.getElementById("parent__add__ticketGroup");

    saveFormData.append('title' , ticketGroupTitle.value)
    if(ticketGroupParent != null){saveFormData.append('parent' , ticketGroupParent.value)}

    // todo : hide modal after click
    if (confirm(  "از صحت اطلاعات وارد شده اطمینان دارید؟")) {
        const response = await fetch("/ticketGroup", {method: "post", body: saveFormData});
        if(!response.ok){
            showErrorPopup('/ticketGroup',response.status ,(await response.text()).toString());
        }else {
            showInfoPopup('/ticketGroup', response.status , (await response.text()).toString());
        }
    }


}

async function deleted(id) {
    //todo : cascade all? - melika
    if (confirm("آیا از حذف تیکت " + id + " اطمینان دارید؟")) {
        const response = await fetch("/ticketGroup/delete/" + id, {
            method: "delete"
        });

        if(!response.ok){
            showErrorPopup('/ticketGroup',response.status ,(await response.text()).toString());
        }else {
            showInfoPopup('/ticketGroup', response.status , (await response.text()).toString());
        }

    }


}