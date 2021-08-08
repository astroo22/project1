/**
 * 
 */
 let userid;
 
 let verifyLoggedIn = async () => {
	let res = await fetch('http://localhost:8080/project1/api/getSession');
	let obj = await res.json();
	
	if(obj.userid < 0){
		location.href = "../html/login.html";
	}
	else{
		userId = obj.userid;
	}	
}
document.getElementById("home").addEventListener('click',() => {
	location.href = "../html/ELanding.html";
	});
document.getElementById("logout").addEventListener('click', async () => {
	let res = await fetch('http://localhost:8080/project1/api/logout');
	userId = -1;
	verifyLoggedIn();
});
let init = async () => {
	await verifyLoggedIn();
	let res = await fetch(`http://localhost:8080/project1/api/getUser?userid=${userId}`);
	let user = await res.json();
	let username = user.username;
	document.getElementById("pain").innerText = `Existance is pain. Enjoy your stay ${username}!`;
	let rows = await retreiveAllReimbursements();
	populateTable(rows);
}

document.getElementById("submitF").addEventListener('click',async() => {
	let status = document.getElementById("status").value;
	console.log(status);
	if(status<3){
		if(status == 0){
			let obj = await retreiveAllPendingReimbursementsByUser();
			populateTable(obj);
		}else if(status == 1){
			let obj = await retreiveAllApprovedReimbursementsByUser();
			populateTable(obj);
		}else if(status == 2){
			let obj = await retreiveAllDeniedReimbursementsByUser();
			populateTable(obj);
		}
	}else{
		let obj = await retreiveAllReimbursementsByUser();
		populateTable(obj);
	}
});

let retreiveAllPendingReimbursementsByUser = async () => {
	let res = await fetch('http://localhost:8080/project1/api/getAllPendingByUser');
	let obj = await res.json();
	return obj;
}
let retreiveAllReimbursementsByUser = async () => {
	let res = await fetch('http://localhost:8080/project1/api/getAllReimbursementsByUser');
	let obj = await res.json();
	return obj;
}
let retreiveAllDeniedReimbursementsByUser = async () => {
	let res = await fetch('http://localhost:8080/project1/api/getAllDeniedByUser');
	let obj = await res.json();
	return obj;
}
let retreiveAllApprovedReimbursementsByUser = async () => {
	let res = await fetch('http://localhost:8080/project1/api/getAllApprovedByUser');
	let obj = await res.json();
	return obj;
}
init();







