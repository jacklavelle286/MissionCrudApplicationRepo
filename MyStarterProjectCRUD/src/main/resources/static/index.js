'use strict';


//making the To-Do list
const idref = document.querySelector("#missionID");
const mission = document.querySelector("#mission"); 
const responsibilityRef = document.querySelector("#responsibility"); 
const orderofimportanceRef = document.querySelector("#orderofimportance"); 
const completedstatusRef = document.querySelector("#completedstatus"); 


// input
const statemission = document.querySelector("#missioninput");
const stateresponsibility = document.querySelector("#responsibilityinput");
const stateorderofimportance = document.querySelector("#orderofimportanceInput");
const statecompletedstatus = document.querySelector("#completedstatusInput");
const statelistvalue = document.querySelector("#listValue");

//the delete input for tasks
const idfordelete = document.querySelector("#deleteID");

//updateInput
const idforupdate = document.querySelector("#updateID");
const updatemission = document.querySelector("#updatemission");
const updateresponsibility = document.querySelector("#updateresponsibility");
const updateorderofimportance = document.querySelector("#updateorderofimportance");
const updatecompletedstatus = document.querySelector("#updatecompletedstatus");
const updateListValue = document.querySelector("#updateTodoList");

//creating my list
const newList = document.querySelector("#newListName");

//updating my list
const idUpdater = document.querySelector("#updatedIDforList");
const listNameUpdater = document.querySelector("#updatedListNametxt");

//making list
const ToDoListID = document.querySelector("#todoID");
const todoListName = document.querySelector("#ListName"); 

//delete inpiut for list
const idListDelete = document.querySelector("#deleteListID")


//displaying the data
const info = (user) => {



    let missionid = user.id;
    let listnameofmission = user.nameofmission;
    let responsibility = user.responsibility;
    let orderofimportance = user.orderofimportance;
    let completedstatus = user.completedstatus;

    //taskID
    let tabletask = document.createElement("p"); // <p> </p>s
    let texttaskid = document.createTextNode(`${taskid}`); // creating a node that will append the tasks below
    tabletask.appendChild(texttaskid); //
    idref.appendChild(tabletask);

    //Mission Table
    let tableone = document.createElement("p"); // <p> </p>s
    let textList = document.createTextNode(`${listName}`); // creating a node that will append the tasks below
    tableone.appendChild(textList); //
    mission.appendChild(tableone);

    //Responsibility
    let tabletwo = document.createElement("p"); // <p> </p>s
    let textCat = document.createTextNode(`${responsibility}`); // creating a node that will append the tasks below
    tabletwo.appendChild(textCat); // 
    responsibility.appendChild(tabletwo);


    //order of importance
    let tablethree = document.createElement("p"); // <p> </p>s
    let textRank = document.createTextNode(`${orderofimportance}`); // creating a node that will append the tasks below
    tablethree.appendChild(textRank); //
    orderofimportanceRef.appendChild(tablethree);


    //Completedstatus
    let tablefour = document.createElement("p"); // <p> </p>s
    let textCompleted = document.createTextNode(`${completedstatus}`); // creating a node that will append the tasks below
    tablefour.appendChild(textCompleted); //    
    completedstatusRef.appendChild(tablefour);

 
}

//fetching data from the DB (for now it is h2 until i get it working )
const retrieveData = () => {
    fetch("http://localhost:9999/MISSIONS/readAll")
    .then((response) => {
        // check that the response is OK (i.e. 200)
        if(response.status !== 200){
            throw new Error("status is not 200");
        }else{
            console.log(response);
            console.log(`response is 200`);
            response.json()
            .then((tasks) =>{ 
                for(let user of mission){   
                    info(user);                     
                }
            })
        }
    }).catch((err) => {
        console.error(err);
    })
}

retrieveData(); 



const createToDoList = () => {
	const missionname = statemission.value;
    const responsibility = stateresponsibility.value;
    const orderofimportance = stateorderofimportance.value;
    const completedstatus = statecompletedstatus.value;
    const listvalue = stateListValue.value;

	let createdata = {
		missionname: missionname,
        responsibility:responsibility,
        orderofimportance: orderofimportance,
        completedstatus: completedstatus,
        myToDo: {id:listvalue}
// i got up to here 
	}

	fetch("http://localhost:8080/MISSIONS/create", {
		method: "POST",
		body: JSON.stringify(createdata),
		headers: { "Content-Type": "application/json" }
	})
		.then(response => response.json())
		.then(info => {
			console.log(info);
			console.log("success");
            location.reload() //reloads the  info
		})
		.catch(err => console.error(`ERROR = ${err}`));
}


const updateToDoList = () => {
    const updateID = idforUpdate.value;
	const missionname = updatemissionname.value;
    const responsibility = updateresponsibility.value;
    const orderofimportance = updateorderofimportance.value;
    const completedstatus = updatecompletedstatus.value;
    const listvalue = updateListValue.value;


	let updateData = {
		name: missionname,
        responsibility:responsibility,
        orderofimportance: orderofimportance,
        completed: completed,
        myToDo: {id:listvalue}

	}

	fetch(`http://localhost:8080/MISSION/update/${updateID}`, {
		method: "PUT",
		body: JSON.stringify(updateData),
		headers: { "Content-Type": "application/json" }
	})
		.then(response => response.json())
		.then(info => {
			console.log(info);
			console.log("success");
            location.reload() //reloads the delete info
		})
		.catch(err => console.error(`ERROR = ${err}`));
}

function deleteAlert() {
    alert ("ID not available, please choose another one");
}

function createAlert() {
    alert ("An Error has occurred, please ensure you are using the correct information");
}

const deleteToDoList = () => {

	const ID = idforDelete.value;


	fetch(`http://localhost:8080/MISSION/delete/${ID}`, {
		method: "DELETE",
	}).then((response) => {
		if (response.status != 204) {
            deleteAlert();
			throw new Error(`status is not 204`);

		} else {
			console.log(response);
			console.log(`response is okay (204)`);
			console.log(`your to do with id ${deleteID} was deleted `);
            location.reload() //reloads the delete info

		}
	}).catch((err) => {
		console.error(err);
	})
}


//RETRIVING DATA
const retrieveToDo = () => {
    fetch("http://localhost:8080/ToDo/readAll")
    .then((response) => {
        // check that the response is OK (i.e. 200)
        if(response.status !== 200){
            throw new Error("status is not 200");
        }else{
            console.log(response);
            console.log(`response is 200`);
            //json returns a PROMISE
            response.json()
            .then((todo) =>{ // all the todo list
              console.log(todo);
                for(let user of todo){
                    todoList(user);  // just the task list                     
                }
            })
        }
    }).catch((err) => {
        console.error(err);
    })
}

retrieveToDo(); 


//displaying data of the LIST
const ToDoList = (ToDo) => {
    

    let myToDoID = ToDo.id; 
    let myListNames = ToDo.listName; 

    //taskID
    let tableToDoID = document.createElement("p"); // <p> </p>
    let textToDoID = document.createTextNode(`${myToDoID}`); // creating a node that will append the tasks below
    tableToDoID.appendChild(textToDoID); //
    ToDoListID.appendChild(tableToDoID);

    //taskID
    let tableToDoName = document.createElement("p"); // <p> </p>
    let textToDoList = document.createTextNode(`${myListNames}`); // creating a node that will append the tasks below
    tableToDoName.appendChild(textToDoList); //
    todoListName.appendChild(tableToDoName);
 
}


const createLIST = () => {

	const todoList = newList.value;

	let data = {
		listName: todoList
	}
	fetch("http://localhost:8080/ToDo/create", {
		method: "POST",
		body: JSON.stringify(data),
		headers: { "Content-Type": "application/json" }
	})
		.then(response => response.json())
		.then(info => {
			console.log(info);
			console.log("success");
            location.reload() //reloads the  info
		})
		.catch(err => console.error(`ERROR = ${err}`));
}



const updateMynList = () => {
    const updatedListID = idUpdater.value;
	const updatedListName = listNameUpdater.value;


	let updateData = {
        listName: updatedListName
	}

	fetch(`http://localhost:8080/ToDo/update/${updatedListID}`, {
		method: "PUT",
		body: JSON.stringify(updateData),
		headers: { "Content-Type": "application/json" }
	})
		.then(response => response.json())
		.then(info => {
			console.log(info);
			console.log("success");
            location.reload() //reloads the  info
		})
		.catch(err => console.error(`ERROR = ${err} ${deleteAlert()}`));
}


const deleteAlist = () => {

	const idList = idListDelete.value;


	fetch(`http://localhost:8080/ToDo/delete/${idList}`, {
		method: "DELETE",
	}).then((response) => {
		if (response.status != 204) {
            deleteAlert(); //id does not exist
			throw new Error(`error not 204`);

		} else {
			console.log(response);
            location.reload() //reloads the delete info

		}
	}).catch((err) => {
		console.error(err);
	})
}
