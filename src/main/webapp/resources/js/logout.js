/**
 * 
 */
 let logoutbtn = document.getElementById("logout").addEventListener("click",logout);
 async function logout(){
	let req = await fetch('http://localhost:8080/project1/api/logout');
	let res = await req.text();
	console.log(res);
	req = await fetch('http://localhost:8080/project1/api/getSession');
	res = await req.text();
	console.log(res);
	location.href = '../html/login.html';
}