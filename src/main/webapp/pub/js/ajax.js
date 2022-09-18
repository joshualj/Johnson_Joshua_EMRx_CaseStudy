// Clinician To User HASH
// const createClinicianToUserHashEle = (clinicianUsers) => {
//     const ele = document.createElement('select');
//     ele.classList.add('form-select', 'mb-3');
//     for (let clinicianId in clinicianUsers) {
//         console.log(clinicianUsers[clinicianId] + "from ajax");
//         ele.append(createClinicianUserEle(clinicianId, clinicianUsers[clinicianId]));
//     }
//     return ele;
// }

// // Hash with Clinician Objects as keys, User Objects as values
// const createClinicianUserEle = (clinicianId, userNames) =>  {
//     console.log(userNames)
//     const ele = document.createElement('option');
//     ele.innerHTML =
//         `
//             <div style="display: inline-block">
//                     ${userNames}
//             </div>
//         `;
//     return ele;
// }

$(document).ready(function(){
    $('#time').change(function(){
        var timeDate = $("#time, #date").serialize();
        $.ajax({
            url: 'ajaxAppointment',
            type: 'GET',
            dataType: 'json',
            data: timeDate,
            success: function(data) {
                if(data.isValid){
                    $('#time').html('Nice, appointments are availible at this selected time and date.');
                    //add logic to replace #clinUsers dropdown with ajax-derived #clinUser options 

                    // $('#clininicianId').find('option').remove();
                } else {
                    alert('Please enter a date and time with availibility');
                }
            }

        });
        return false;
    });
});