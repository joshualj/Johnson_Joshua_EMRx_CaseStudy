//This page uses jquery and JSON to append rows to the #appointmentsTable from appointment_schedule.html

// $(document).ready(function() {
//     //JSON example of appointment schedule for one day
//     let appointment = {
//         appointment1: {
//             "time":"8:00 - 8:30",
//             "patient_id":"null",
//             "chief_complaint":"null"
//         },
//         appointment2: {
//             "time":"8:00 - 9:00",
//             "patient_id":"Peter",
//             "chief_complaint":"null"
//         },
//         appointment3: {
//             "time":"9:00 - 9:30",
//             "patient_id":"Billy",
//             "chief_complaint":"null"
//         },
//         appointment4: {
//             "time":"9:30 - 10:00",
//             "patient_id":"Bob",
//             "chief_complaint":"null"
//         },
//         appointment5: {
//             "time":"10:00 - 10:30",
//             "patient_id":"null",
//             "chief_complaint":"null"
//         },
//         appointment6: {
//             "time":"10:30 - 11:00",
//             "patient_id":"Peter",
//             "chief_complaint":"null"
//         },
//         appointment7: {
//             "time":"11:00 - 11:30",
//             "patient_id":"Billy",
//             "chief_complaint":"null"
//         },
//         appointment8: {
//             "time":"11:30 - 12:00",
//             "patient_id":"Bob",
//             "chief_complaint":"null"
//         },
//         appointment9: {
//             "time":"12:00 - 12:30",
//             "patient_id":"Billy",
//             "chief_complaint":"null"
//         },
//         appointment10: {
//             "time":"12:30 - 1:00",
//             "patient_id":"Bob",
//             "chief_complaint":"null"
//         },
//         appointment11: {
//             "time":"1:00 - 1:30",
//             "patient_id":"null",
//             "chief_complaint":"null"
//         },
//         appointment12: {
//             "time":"2:00 - 2:30",
//             "patient_id":"Peter",
//             "chief_complaint":"null"
//         },
//         appointment13: {
//             "time":"3:00 - 3:30",
//             "patient_id":"Billy",
//             "chief_complaint":"null"
//         },
//         appointment14: {
//             "time":"3:30 - 4:00",
//             "patient_id":"Bob",
//             "chief_complaint":"null"
//         }
//     }
//
//     $.each(appointment, function(i){
//         //for each appointment, grab the time & patient id
//         let time = appointment[i].time;
//         let patient_id = appointment[i].patient_id;
//
//         //if an appointment slot has no patient_id, add the time slot to the #appointments table row
//         //and include a green button for user to click on to schedule an appointment at that time
//         //under the 'availibility' column
//         if(patient_id =="null") {
//             console.log(time);
//             $('#appointmentsTable').append (
//                 `<tr height="50px">
//                     <td><b>`+time+`</b></td>
//                     <td><b>
//                         <button type="button" class="btn btn-success"><i class="far fa-trash-alt"></i></button>
//                     </b></td>
//                 </tr>`
//             )
//         } else {
//             //if a time slot already has a patient, add the time slot to the #appointments table row
//             //and include 'N/A' under the 'availibility' column
//             $('#appointmentsTable').append (
//                 `<tr height="50px">
//                 <td><b>`+time+`</b></td>
//                 <td><b>N/A</b></td>
//             </tr>`
//             )
//         }
//     });
// });
