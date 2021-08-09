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
document.getElementById("home").addEventListener('click', () => {
	location.href = "../html/MLanding.html";
	});
document.getElementById("logout").addEventListener('click', async () => {
	let res = await fetch('http://localhost:8080/project1/api/logout');
	userId = -1;
	verifyLoggedIn();
	location.href = "../html/login.html";
});
let init = async () => {
	await verifyLoggedIn();
	let res = await fetch(`http://localhost:8080/project1/api/getUser?userid=${userId}`);
	let user = await res.json();
	let username = user.username;
	document.getElementById("pain").innerText = `Managers managing page! big power much wow ${username}!`;
	let res2 = await getPendingReimbursements();
	populateTable(res2);
}
let populateTable = (list)=>{
	let table = document.getElementById("rtable");
	table.innerHTML = '<tr><th>Status</th><th>Type</th><th>Amount</th><th>Empolyee</th><th>Submitted Date</th><th>Approve/Deny</th></tr>';
	list.forEach((obj)=> {
		let count =1;
		let row=table.insertRow(count++);
		row.id = obj.id;

		let status = row.insertCell(0);
		status.innerHTML = obj.rStatus.r_status;
		
		let type = row.insertCell(1);
		type.innerHTML = obj.type.r_type;
		
		let subBy = row.insertCell(2);
		subBy.innerHTML = obj.employee.username;
		
		let amount = row.insertCell(3);
		amount.innerHTML = Number(obj.reimb_Amount).toFixed(3);
		
		let submitted = row.insertCell(4);
		submitted.innerHTML = new Date(obj.submitted).toDateString();
		
		let appNden = row.insertCell(5);
		appNden.innerHTML = `<button onclick="approve(${obj.id})" id="approve">Approve</button> <button onclick="deny(${obj.id})" id="deny">Deny</button>`;
		
		let drow = table.insertRow(count++);
		let desc = drow.insertCell(0);
		desc.setAttribute("colspan", "7");
		desc.className = "description";
		desc.innerHTML = `<h3>Description:</h><p>${obj.description}</p>`;
		
	});
}
let getPendingReimbursements = async ()=>{
	let res = await fetch(`http://localhost:8080/project1/api/getAllPending`);
	let obj = await res.json();
	console.log(obj);
	return obj;
}
let approve = async (id)=>{
	await verifyLoggedIn();
	let res = await fetch(`http://localhost:8080/project1/api/getUser?userid=${userId}`);
	let user = await res.json();
	console.log(user);
	
	console.log(`attempting to approve reimbursement ${id}`);
	
	let manager = user.id;
	
	obj = {
		manager_id : manager,
		rid : id,
	}
	let res2 = await fetch(`http://localhost:8080/project1/api/approveReimbursement`,
	{
		method: 'POST',
		headers:{
			'Content-Type': 'application/json'
			},
		body: JSON.stringify(obj)
	});
	let list = await getPendingReimbursements();
	populateTable(list);
}
let deny = async (id)=>{
	await verifyLoggedIn();
	let res = await fetch(`http://localhost:8080/project1/api/getUser?userid=${userId}`);
	let user = await res.json();
	console.log(user);
	
	console.log(`attempting to deny reimbursement ${id}`);
	
	let manager = user.id;
	
	obj = {
		manager_id : manager,
		rid : id,
	}
	let res2 = await fetch(`http://localhost:8080/project1/api/denyReimbursement`,
	{
		method: 'POST',
		headers:{
			'Content-Type': 'application/json'
			},
		body: JSON.stringify(obj)
	});
	let list = await getPendingReimbursements();
	populateTable(list);
}

init();
	
	