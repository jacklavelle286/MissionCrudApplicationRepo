'use strict';


//making my todolist
const idref = document.querySelector("#taskID");
const task = document.querySelector("#task"); 
const categoryRef = document.querySelector("#category"); 
const rankRef = document.querySelector("#rank"); 
const completeRef = document.querySelector("#complete"); 


//delete inpiut for tasks
const idforDelete = document.querySelector("#deleteID");


//creating input
const stateTask = document.querySelector("#taskinput");
const stateCategory = document.querySelector("#categoryInput");
const stateRank = document.querySelector("#rankInput");
const stateCompleted = document.querySelector("#completedInput");
const stateListValue = document.querySelector("#listValue");


//updateInput
const idforUpdate = document.querySelector("#updateID");
const updateTask = document.querySelector("#updateTask");
const updateCategory = document.querySelector("#updateCategory");
const updateRank = document.querySelector("#updateRank");
const updateCompleted = document.querySelector("#updateCompleted");
const updateListValue = document.querySelector("#updateTodoList");




//making my list
const ToDoListID = document.querySelector("#todoID");
const todoListName = document.querySelector("#ListName"); 


//creating my list
const newList = document.querySelector("#newListName");

//updating my list
const idUpdater = document.querySelector("#updatedIDforList");
const listNameUpdater = document.querySelector("#updatedListNametxt");


//delete inpiut for list
const idListDelete = document.querySelector("#deleteListID");


//displaying data
const info = (user) => {



    let taskid = user.id;
    let listName = user.name;
    let category = user.category;
    let rank = user.rank;
    let completed = user.completed;

    //taskID
    let tabletask = document.createElement("p"); // <p> </p>s
    let texttaskid = document.createTextNode(`${taskid}`); // creating a node that will append the tasks below
    tabletask.appendChild(texttaskid); //
    idref.appendChild(tabletask);

    //task
    let tableone = document.createElement("p"); // <p> </p>s
    let textList = document.createTextNode(`${listName}`); // creating a node that will append the tasks below
    tableone.appendChild(textList); //
    task.appendChild(tableone);

    //category
    let tabletwo = document.createElement("p"); // <p> </p>s
    let textCat = document.createTextNode(`${category}`); // creating a node that will append the tasks below
    tabletwo.appendChild(textCat); // 
    categoryRef.appendChild(tabletwo);


    //rank
    let tablethree = document.createElement("p"); // <p> </p>s
    let textRank = document.createTextNode(`${rank}`); // creating a node that will append the tasks below
    tablethree.appendChild(textRank); //
    rankRef.appendChild(tablethree);


    //Completed
    let tablefour = document.createElement("p"); // <p> </p>s
    let textCompleted = document.createTextNode(`${completed}`); // creating a node that will append the tasks below
    tablefour.appendChild(textCompleted); //    
    completeRef.appendChild(tablefour);

 
}

//fetching data
const retrieveData = () => {
    fetch("http://localhost:8080/task/readAll")
    .then((response) => {
        // check that the response is OK (i.e. 200)
        if(response.status !== 200){
            throw new Error("status is not 200");
        }else{
            console.log(response);
            console.log(`response is 200`);
            //json returns PROMISE
            response.json()
            .then((tasks) =>{ // all the todo list
             //   console.log(tasks);
                for(let user of tasks){
                    //console.log(user);
                    info(user);  // just the task list                     
                }
            })
        }
    }).catch((err) => {
        console.error(err);
    })
}

retrieveData(); 



const createToDoList = () => {
	const taskname = stateTask.value;
    const category = stateCategory.value;
    const rank = stateRank.value;
    const completed = stateCompleted.value;
    const listvalue = stateListValue.value;

	let createdata = {
		name: taskname,
        category:category,
        rank: rank,
        completed: completed,
        myToDo: {id:listvalue}

	}

	fetch("http://localhost:8080/task/create", {
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
	const taskname = updateTask.value;
    const category = updateCategory.value;
    const rank = updateRank.value;
    const completed = updateCompleted.value;
    const listvalue = updateListValue.value;


	let updateData = {
		name: taskname,
        category:category,
        rank: rank,
        completed: completed,
        myToDo: {id:listvalue}

	}

	fetch(`http://localhost:8080/task/update/${updateID}`, {
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
    alert ("Sorry! That ID is not in the table, please select the corred ID ");
}

function createAlert() {
    alert ("Sorry! something went wrong, please ensure to type the correct data! ");
}

const deleteToDoList = () => {

	const ID = idforDelete.value;


	fetch(`http://localhost:8080/task/delete/${ID}`, {
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
    fetch("http://localhost:8080/todo/readAll")
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
const todoList = (todo) => {
    

    let myToDoID = todo.id; 
    let myListNames = todo.listName; 

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
	fetch("http://localhost:8080/todo/create", {
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

	fetch(`http://localhost:8080/todo/update/${updatedListID}`, {
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


	fetch(`http://localhost:8080/todo/delete/${idList}`, {
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
