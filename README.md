# EMRxpress

In the demanding, appointment-filled day, medical clinicians are required to accurately document patient visits in a time-efficient manner. Electronic Medical Record (EMR) software programs allow medical clinicians to log medical notes about each patients' complaint. Time efficiency and accuracy are essential.

EMRxpress seeks to streamline the medical documentation process by allowing patients to submit a Pre-Appointment Questionairre (PAQ). The PAQ allows a patient to answer relevant questions about their medical concern -- including the complaint itself (i.e. headache), the time they first experienced the complaint (i.e. 2 weeks), and the character of the complaint (i.e. sharp) -- prior to the visit. The clinician can view their patient's PAQ prior to an appointment, allowing the clinician to efficiently tailor questions to the complaint during the visit.

Similar to a traditional Electronic Medical Record program, EMRxpress gives a clinician the options to view/edit/cancel their appointments. In addition, patient users can schedule and view upcoming appointments.

## Usage
Login
![patientRegistration.png](src/main/webapp/pub/images/login.png)

A patient may register using the following form. 
![patientRegistration.png](src/main/webapp/pub/images/patientRegistration.png)

A patient may schedule an appointment as follows:
![scheduleAppointment.png](src/main/webapp/pub/images/scheduleAppointment.png)

A patient may view their profile, which includes their appointments, as follows:
![scheduleAppointment.png](src/main/webapp/pub/images/patientProfile.png)

A patient may click the "start/edit" button displayed in the appointment table to be taken to a Pre-Appointment Questionnaire:
![scheduleAppointment.png](src/main/webapp/pub/images/paq.png)

A clinician may view their patient schedule for a specific day. From this page, they may click "View" to view a patient's PAQ, "Edit" to edit an appointment's time, date, or clinician, or "Cancel" to cancel the appointment.
![scheduleAppointment.png](src/main/webapp/pub/images/clinicianScheduleViewer.png)

A clinician may edit an appointment's date, time, or clinician.
![scheduleAppointment.png](src/main/webapp/pub/images/appointmentEditor.png)

A clinician may register another clinician user
![scheduleAppointment.png](src/main/webapp/pub/images/clinicianRegistration.png)

Any user may search a clinician by Language, Department, or Last Name
![scheduleAppointment.png](src/main/webapp/pub/images/clinicianSearch.png)

## Database
Database structure, via ER diagram:

![EMRx_April21.png](src/main/webapp/pub/images/dataTables.png)

## Technology
HTML, CSS, Bootstrap, JavaScript, Java, 
Spring, Lombok, Junit, SLF4J, MariaDB, 
Spring, Hibernate, JPA, Maven, Tomcat, JSP

## Questions
Feel free to contact me with questions at joshualj2018@gmail.com.