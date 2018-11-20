
function Contact(firstName, lastName, phone) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
}

$(document).ready(function () {
    // var contactList = [];
    var btn = $("#addContact");
    btn.on("click", function () {
        var firstName = $("#firstName");
        var lastName = $("#lastName");
        var phone = $("#phone");
        var contact = new Contact(firstName.val(), lastName.val(), phone.val());
        $.ajax({
            type: "POST",
            url: "phoneBook/rcp/api/v1/addContact",
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(contact)
        }).done(function (response) {
            //console.log(response);
            //console.log(JSON.parse(response));
            //console.log("Добавилось. Всё ОК");
            //console.log(response);

        }).fail(function (ajaxRequest) {
            var contactValidation = $.parseJSON(ajaxRequest.responseText);
        }).always(function () {
            $.ajax({
                type: "GET",
                url: "phoneBook/rcp/api/v1/getAllContacts",
                success: function (response) {
                    // var contacts = JSON.parse(response);
                    //drawTable(contacts);
                    drawTable(response);
                }
            });
            //console.log("Всё ОК");
            firstName.val("");
            lastName.val("");
            phone.val("");
        });
    });

    $.ajax({
        type: "GET",
        url: "phoneBook/rcp/api/v1/getAllContacts",
        success: function (response) {
            // var contacts = JSON.parse(response);
            // drawTable(contacts);
            drawTable(response);
        }
    });
    var drawTable = function (contacts) {
        var contactList = [];
        contactList = contacts;
        $(".tbody").find("tr").remove();
        if (!contacts.length == 0) {
            _.each(contacts, function (el, index) {
                var tr = $("<tr class='tr'><td></td><td></td><td></td><td></td><td></td>" +
                    "<td><button class='btn btn-primary delete-btn'  type='button'>" +
                    "Удалить</button></td></tr>");
                tr.find("td:eq(0)").html("<input type=\"checkbox\" class=\"select-me\"/>");
                tr.find("td:eq(1)").text(el.number);
                tr.find("td:eq(2)").text(el.lastName);
                tr.find("td:eq(3)").text(el.firstName);
                tr.find("td:eq(4)").text(el.phone);
                $(".table").find(".tbody").append(tr);

            });
            deleteContact(contactList);
        }
    };

    function deleteContact(contactList) {
        var deleteBtn = $(".delete-btn");
        deleteBtn.on("click", function () {
            var number = $(this).closest("tr").find("td:eq(1)").text();
            console.log(contactList);
            var contact = _.find(contactList, function (el, index) {
                return el.number == number;
            });
            console.log(contact);
            //var firstName = $(this).closest("tr").find("td:eq(3)").text();
            //var lastName = $(this).closest("tr").find("td:eq(2)").text();
            //var phone = $(this).closest("tr").find("td:eq(4)").text();
            //var contact = new Contact(firstName, lastName, phone);
            //contact.id = id;
            //console.log("Delete Button Pressed");
            $.ajax({
                type: "POST",
                url: "/phoneBook/rcp/api/v1/deleteContact",
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(contact)
            }).always(function (response) {
                $.ajax({
                    type: "GET",
                    url: "phoneBook/rcp/api/v1/getAllContacts",
                    success: function (response) {
                        drawTable(response);
                    }
                })
            });
        });
    }

    var deleteChecked = $("#deleteChecked");
    deleteChecked.on('click', function () {
        var listOfChecked = $(".tbody").find(".select-me");
        var indexList = [];
        var contact = null;
        listOfChecked.each(function (index, e) {
            if ($(e).is(":checked")) {
                //indexList.push($(this).closest("tr").find("td:eq(1)").text());
                var id = $(this).closest("tr").find("td:eq(1)").text();
                var firstName = $(this).closest("tr").find("td:eq(3)").text();
                var lastName = $(this).closest("tr").find("td:eq(2)").text();
                var phone = $(this).closest("tr").find("td:eq(4)").text();
                contact = new Contact(firstName, lastName, phone);
                contact.id = id;
                indexList.push(contact);
                console.log(indexList);
            }

        });
        $.ajax({
            type: "POST",
            url: "/phoneBook/rcp/api/v1/deleteChecked",
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(indexList)
            // data: JSON.stringify(indexList)
        }).always(function (response) {
            $.ajax({
                type: "GET",
                url: "phoneBook/rcp/api/v1/getAllContacts",
                success: function (response) {
                    drawTable(response);
                }
            })
        });

    })
});



