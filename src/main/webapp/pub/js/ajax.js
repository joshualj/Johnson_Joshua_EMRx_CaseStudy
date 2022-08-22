// Clinician To User HASH
const createClinicianToUserHashEle = (clinicianUsers) => {
    const ele = document.createElement('select');
    ele.classList.add('form-select', 'mb-3');
    for (let clinicianId in clinicianUsers) {
        console.log(clinicianUsers[clinicianId] + "from ajax");
        ele.append(createClinicianUserEle(clinicianId, clinicianUsers[clinicianId]));
    }
    return ele;
}

// Hash with Clinician Objects as keys, User Objects as values
const createClinicianUserEle = (clinicianId, userNames) =>  {
    console.log(userNames)
    const ele = document.createElement('option');
    ele.innerHTML =
        `
            <div style="display: inline-block">
                    ${userNames}
            </div>
        `;
    return ele;
}