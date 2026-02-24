const API_URL = "/courses";

let editing = false;

function fetchCourses() {
    fetch(API_URL)
        .then(res => res.json())
        .then(data => {
            const list = document.getElementById("courseList");
            list.innerHTML = "";

            data.forEach(course => {
                list.innerHTML += `
                    <div class="course-card">
                        <h3>${course.name}</h3>
                        <p>${course.duration}</p>
                        <p>${course.price}</p>
                        <p>${course.description}</p>
                        <button onclick="editCourse(${course.id})">Edit</button>
                        <button onclick="deleteCourse(${course.id})">Delete</button>
                    </div>
                `;
            });
        });
}

function saveCourse() {
    const id = document.getElementById("courseId").value;

    const course = {
        name: document.getElementById("name").value,
        duration: document.getElementById("duration").value,
        price: document.getElementById("price").value,
        description: document.getElementById("description").value,
        startDateTime: document.getElementById("startDateTime").value
    };

    if (id) {
        // UPDATE
        fetch(`${API_URL}/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(course)
        })
        .then(() => {
            showToast("Updated Successfully!");
            resetForm();
            fetchCourses();
        });
    } else {
        // ADD
        fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(course)
        })
        .then(() => {
            showToast("Added Successfully!");
            resetForm();
            fetchCourses();
        });
    }
}

function editCourse(id) {
    fetch(`${API_URL}/${id}`)
        .then(res => res.json())
        .then(course => {
            document.getElementById("courseId").value = course.id;
            document.getElementById("name").value = course.name;
            document.getElementById("duration").value = course.duration;
            document.getElementById("price").value = course.price;
            document.getElementById("description").value = course.description;
            document.getElementById("startDateTime").value =
                course.startDateTime.substring(0,16);

            document.getElementById("submitBtn").innerText = "Update Course";
        });
}

function deleteCourse(id) {
    fetch(`${API_URL}/${id}`, { method: "DELETE" })
        .then(() => {
            showToast("Deleted Successfully!");
            fetchCourses();
        });
}

function resetForm() {
    document.getElementById("courseId").value = "";
    document.getElementById("name").value = "";
    document.getElementById("duration").value = "";
    document.getElementById("price").value = "";
    document.getElementById("description").value = "";
    document.getElementById("startDateTime").value = "";
    document.getElementById("submitBtn").innerText = "Add Course";
}

function showToast(message) {
    const toast = document.getElementById("toast");
    toast.innerText = message;
    toast.style.display = "block";

    setTimeout(() => {
        toast.style.display = "none";
    }, 2000);
}

window.onload = fetchCourses;
