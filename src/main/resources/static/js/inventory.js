function onInventorySubmit() {
    const name = document.querySelector("#createInventoryEntryName").value
    const quantity = document.querySelector("#createInventoryEntryQuantity").value

    if (name !== '' && quantity !== '') {
        fetch("/inventory/create", {
            method: "POST",
            headers: {"content-type": "application/json"},
            body: JSON.stringify({
                name: name,
                quantity: quantity
            })
        })
            .then(response => response.json())
            .then(data => console.log(data))
            .then(() => window.location.reload())
    }
}

function onInventorySubmitUpdate(id) {
    fetch(`/inventory/${id}`, {
        method: "PUT",
        headers: {"content-type": "application/json"},
        body: JSON.stringify({
            name: "Updated Item!",
            quantity: 2
        })
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .then(() => window.location.reload())
}

function onInventoryDelete(id) {
    fetch(`/inventory/${id}`, {
        method: "DELETE"
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .then(() => window.location.reload())
}