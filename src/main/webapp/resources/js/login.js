/**
 * 
 */
let form = document.getElementById('login').addEventListener('submit', login);
async function login(e) {
	e.preventDefault();
	let username = document.getElementById('username').value;
	let password = document.getElementById('password').value;

	let user = {
		username,
		password
	};

	console.log(user);
	//console.log(res);

	try {
		let req = await fetch('http://localhost:8080/project1/api/login', {
			method: 'POST',
			headers:{
				'Content-Type' : 'application/json'
			},
			body: JSON.stringify(user)
		});
		let res = await req.json();
		console.log(res);
		if(res.role==='EMPLOYEE'){
			location.href = 'resources/html/ELanding.html';
		}else {
			location.href = 'resources/html/MLanding.html';
		}
} catch (e) {
	alert('Username or password was incorrect');
	return;
}
}
	//sudo look to change
		//if(res.userrole==="EMPLOYEE")
	/*
		if (res.userRole.EMPLOYEE) {
			location.href = 'resources/html/Employee.html';
		}
		if (res.userRole.MANAGER) {
			location.href = 'resources/html/Manager.html';
		}
		try{
		let req = await fetch('http://localhost:8080/SocialHubWeek4/api/login', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(user)
		});
		let res = await req.json();
		
	if(res.userrole==="EMPLOYEE")
	 {
		location.href = 'resources/html/Employee.html';
	 }
	
	else{
		location.href = 'resources/html/Manager.html';
	}
	
		console.log(res);
	} catch(e){
		alert('Username or password incorrect!');
		return;
	}

		*/