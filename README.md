# Todo Application

A simple Todo application built with Spring Boot, Kotlin, and MongoDB. This application provides a basic API for managing a list of todo items. Users can create, retrieve (by owner), and update todo items. Each todo item has a title, description, creation timestamp, color, owner ID, and priority.

**Technologies Used:** Spring Boot, Kotlin, MongoDB, Gradle.

**API Endpoints:**

* **Create a Todo Item (`POST /todo`):**
    ```json
    {
        "title": "Buy groceries",
        "desc": "Milk, eggs, bread, and cheese",
        "color": 4294967040,
        "priority": "P1"
    }
    ```
    Response:
    ```json
    {
        "id": "6459a947d140552a843a978b",
        "title": "Buy groceries",
        "desc": "Milk, eggs, bread, and cheese",
        "color": 4294967040,
        "createdAt": "2025-05-05T13:00:00.000Z"
    }
    ```
* **Get Todo Items by Owner ID (`GET /todo?ownerId={ownerId}`):** Example: `GET http://localhost:8085/todo?ownerId=6459a93ad140552a843a978a`. Response (array):
    ```json
    [
        {
            "id": "6459a947d140552a843a978b",
            "title": "Buy groceries",
            "desc": "Milk, eggs, bread, and cheese",
            "color": 4294967040,
            "createdAt": "2025-05-05T13:00:00.000Z"
        },
        {
            "id": "6459a950d140552a843a978c",
            "title": "Walk the dog",
            "desc": "Take the dog for a 30-minute walk in the park.",
            "color": 4282597560,
            "createdAt": "2025-05-05T13:05:00.000Z"
        }
    ]
    ```
* **Update a Todo Item (`PUT /todo/{id}`):** Example: `PUT http://localhost:8085/todo/6459a947d140552a843a978b`. Request Body:
    ```json
    {
        "title": "Updated Grocery List",
        "desc": "Organic milk, free-range eggs, whole wheat bread",
        "color": 4278190335,
        "priority": "P0"
    }
    ```
    Response:
    ```json
    {
        "id": "6459a947d140552a843a978b",
        "title": "Updated Grocery List",
        "desc": "Organic milk, free-range eggs, whole wheat bread",
        "color": 4278190335,
        "createdAt": "2025-05-05T13:00:00.000Z"
    }
    ```
    Response Codes: `200 OK` (updated), `404 Not Found` (ID not found).

**Data Model:**
```kotlin
data class Todo(
    val title: String,
    val description: String,
    val createdAt: Instant,
    val color: Long,
    val ownerId: ObjectId,
    val priority: Priority,
    @Id val id: ObjectId = ObjectId.get()
) {
    enum class Priority { PO, P1, P2, P3, P4 }
}
