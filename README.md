# **Capstone Project - Product Catalog using FakeStoreAPI**

This is a **Capstone Project** developed to demonstrate the integration of **FakeStoreAPI** for creating a Product Catalog in a Spring Boot application. The application allows users to access a variety of products from the **FakeStoreAPI** and provides functionalities such as viewing product details, listing all products, and simulating product catalog operations.

### **Project Overview**

This project simulates a **Product Catalog** service by consuming data from the publicly available **FakeStoreAPI** (https://fakestoreapi.com), a REST API that provides details of various products, such as electronics, fashion, and other general items. The **Capstone Project** is a simple Spring Boot application that retrieves data from this API, processes it, and exposes it to consumers through various endpoints.

### **Features of the Project:**

- **Product Listing**: Users can view a list of products available in the catalog.
- **Product Detail**: Users can view detailed information about individual products such as title, price, description, and image.
- **REST API Integration**: The project integrates with the **FakeStoreAPI** to retrieve product data.
- **Spring Boot Backend**: The backend is built using **Spring Boot** to expose endpoints and handle business logic for managing products.
  
### **Technologies Used:**

- **Spring Boot**: Framework for building the backend API.
- **REST API**: Integration with **FakeStoreAPI** for fetching product data.
- **JSON**: Data format for transferring product details.
- **Java**: Programming language used to implement the application.
- **Spring Web**: To expose RESTful endpoints for accessing product information.
- **Spring Boot RestTemplate**: For consuming the external API (FakeStoreAPI).
  
---

### **How the Project Works**

1. **Fetching Products**: The **Product Catalog** service makes HTTP requests to **FakeStoreAPI** to fetch the list of products. The data returned by the API includes product details such as the title, description, price, image URL, and category.
2. **Displaying Products**: The fetched product data is stored in memory and served via RESTful API endpoints in the Spring Boot application.

---

### **Project Structure**

- **src/main/java/com/scaler/capstone**: Contains the main Spring Boot application, services, and controllers.
  - **ProductService**: Service that interacts with the external FakeStoreAPI to fetch product data.
  - **ProductController**: REST controller that exposes the product endpoints.
  - **Product**: Model class representing a product entity.

- **src/main/resources/application.properties**: Contains application configurations like API base URL and other settings.

---

### **How to Run the Project Locally**

1. **Clone the repository:**
   ```bash
   git clone https://github.com/n3hifriends/capstone_project.git
   ```

2. **Navigate to the project directory:**
   ```bash
   cd capstone_project
   ```

3. **Build the project using Maven:**
   ```bash
   mvnw clean install
   ```

4. **Run the application:**
   ```bash
   mvnw spring-boot:run
   ```

5. **Access the API:**
   - The application will be running on `http://localhost:8080`.
   - **List all products**: `GET /api/products`
   - **View product details**: `GET /api/products/{id}` (where `id` is the product ID from the list)

---

### **API Endpoints**

- `GET /api/products`: Fetches all products from the **FakeStoreAPI** and returns a list of products.
- `GET /api/products/{id}`: Fetches a single product's details based on the provided product `id`.

---

### **Example API Requests**

1. **Fetch all products**:
   ```bash
   curl -X GET http://localhost:8080/api/products
   ```

2. **Fetch product details by ID**:
   ```bash
   curl -X GET http://localhost:8080/api/products/1
   ```

---

### **Sample JSON Response for All Products**

```json
[
  {
    "id": 1,
    "title": "Product 1",
    "price": 19.99,
    "description": "Description of product 1",
    "image": "https://example.com/image.jpg",
    "category": "electronics"
  },
  {
    "id": 2,
    "title": "Product 2",
    "price": 29.99,
    "description": "Description of product 2",
    "image": "https://example.com/image.jpg",
    "category": "clothing"
  }
]
```

---
This is deployed in AWS
---
### **Future Enhancements**

- **User Authentication**: Add user authentication and authorization to allow users to manage their own product catalogs.
- **Product Search**: Implement search functionality to filter products based on categories, price, etc.
- **Database Integration**: Integrate with a relational database (e.g., MySQL, PostgreSQL) to persist products and product details.

---
