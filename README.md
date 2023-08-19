# Only Home Food

## Database Design

- [ ] Create an ER diagram of the database
- [ ] Write Create table scripts [script](/src/main/resources/db/migration/V1__create_users.sql)
- [ ] [ ER DIAGRAM![HbqqXef.md.png](https://iili.io/HbqqXef.md.png)](https://freeimage.host/i/HbqqXef)

## Project Setup

- [ ] Create a new Java project
- [ ] Set up a MySQL database
- [ ] Add necessary libraries
	- [ ] JDBC, 
	- [ ] MySQL Connector, 
	- [ ] JUnit, 
	- [ ] Dotenv

## Module: User

- [ ] Create User table
- [ ] Create User model

### Feature 1: Create User

#### User Story:
User can create his new account

#### Pre-requisites:

- [ ] User DAO (create) 
- [ ] User Service (create)

#### Validations:

- [ ] Form Validation
		- user null
		- name (null, empty, pattern)
		- email  (null, empty, pattern)
		- password  (null, empty, pattern)
		- phone_number (length >= 60000000001 and length <= 9999999999)

- [ ] Business Validation
		- phone_number or Email Already Exist

#### Messages:
- [ ] User cannot be null
- [ ] Name cannot be null or empty
- [ ] Email cannot be null or empty
- [ ] Password cannot be null or empty
- [ ] Invalid Email Id
- [ ] Mobile Number must start between 6 - 9 and have 10 digits
- [ ] Invalid String Pattern
- [ ] Password does not match the requested pattern
- [ ] User already exist

#### Flow: 
```mermaid
graph TD;
  A["User Service: Create new User(User user)"] --> B["Form Validation"] -- Valid --> C{Business Validation}
  B -- Invaild --> G[Validation Exception]
  C -- Valid --> D["Create User"]
  D --> E["New User Created"]
  C -- Invalid --> F["Validation Exception"]
```

### Feature 2: Update User

#### User Story
Existing User can update his details

#### Pre-requisites:

- [ ] User DAO (update) 
- [ ] User Service (update)

#### Validations:

- [ ] Form Validation
		- id <= 0
		- User null
		- name (null, empty, pattern)
		- password  (null, empty, pattern)

- [ ] Business Validation
		- Check Id is present
	
#### Messages:
- [ ] User cannot be null
- [ ] Name cannot be null or empty
- [ ] password cannot be null or empty
- [ ] User Id cannot be zero or below zero
- [ ] Invalid String Pattern
- [ ] Password does not match the requested pattern
- [ ] User not found

#### Flow: 
```mermaid
graph TD;
  A["User Service: Update Existing User"] --> B["Form Validation"]-- Valid --> C{Business Validation}
  B -- Invalid--> G["Validation Exception"]
  C -- Valid --> D["Update User"]
  C -- Invalid --> F["Validation Exception"]
  D --> E["User Details Updated"]
```

### Feature 3: Delete User

#### User Story
User can inactive his account

#### Pre-requisites:

- [ ] User DAO (delete) 
- [ ] User Service (delete)

#### Validations:

- [ ] Form Validation
		- id <= 0
		- is active = 0

- [ ] Business Validation
		- Check Id is present

#### Messages:
- [ ] User Id cannot be zero or below zero
- [ ] User not found

#### Flow: 
```mermaid
graph TD;
  A["User Service: Delete User"] --> B["Form Validation"]
  B -- Valid --> C{Business Validation}
  B -- Invalid--> G[Validation Exception]
  C -- Valid --> D[Delete User]
  C -- Invalid --> F[Validation Exception]
  D --> E[User Deleted]
```


## Module: Products

- [ ] Create Product table
- [ ] Create Product Price table
- [ ] Create Product model
- [ ] Create Product Price model

### Feature 1: Create Product

Create new Product

#### Pre-requisites:

- [ ] Product DAO (create) 
- [ ] Product Price DAO (create)
- [ ] Product Price Service (create)
- [ ] Product Service (create)

#### Validations:

- [ ] Form Validation
		- product null
		- name (null, empty, pattern)
		- food_type (null, empty, pattern)
		- quantity_type (null, empty, pattern)
		- price <= 0 and price >= 1000
		- quantity<= 0 and quantity>= 1000
		- id <=0

- [ ] Business Validation
		- Check Food name already exist
		- Check Product Id already exist

#### Messages:

- [ ] Product cannot be null
- [ ] Name cannot be null or empty
- [ ] Food type cannot be null or empty
- [ ] Quantity type cannot be null or empty
- [ ] Price cannot be zero or below zero
- [ ] Quantity cannot be zero or below zero
- [ ] Set Price range between 1 and 1000
- [ ] Set Quantity range between 1 and 1000
- [ ] Invalid String Pattern
- [ ] Product already exist

#### Flow: 
```mermaid
graph TD;
  A["Product Service: Create New Product"] --> 
  B["Form Validation"] -- Valid--> C{Business Validation}
  B -- Invalid --> G[Validation Exception]
  C -- Valid --> D["Create Product (Returns Product id)"]
  C -- Invalid --> F[Validation Exception]
  D --> E[Product Price Service: Create Price]
  E --> H[Form Validation] -- Valid --> I{BusinesValidation}
  H -- Invalid --> J[Validation Exception]
  I -- Valid --> K[Create Price]
  I -- Invalid --> L[Validation Exception]
  K --> M[Product Created Sucessfully]
  
```

### Feature 2:  List All Products

#### Pre-requisites:
- [ ] Product Service(getAll)
- [ ] Product Price DAO(getPrices)
- [ ] Product DAO(findAll)

#### Flow: 
```mermaid
graph TD;
  A["Product Service: GetAll Product"] --> B["FindAll User"]
  B -- Yes --> C[Product Price DAO : GetPrice]
  B -- No --> D["Return null"]
  C --> E[Returns All Product]
```

### Feature 3: Update Product

#### Pre-requisites:

- [ ] Product DAO (update) 
- [ ] Product Service (update)
- [ ] Product Price Service (update)

#### Validations:

- [ ] Form Validation
		- product null
		- id <= 0
		- name (null, empty, pattern)
		- food_type (null, empty, pattern)
		- quantity_type (null, empty, pattern)
		- price <= 0 and price >= 1000
		- quantity<= 0 and quantity>= 1000

- [ ] Business Validation
		- Check product Id is present
	
#### Messages:
- [ ] Product cannot be null
- [ ] Food type cannot be null or empty
- [ ] Quantity type cannot be null or empty
- [ ] Product Id cannot be zero or below zero
- [ ] Price cannot be zero or below zero
- [ ] Quantity cannot be zero or below zero
- [ ] Set Price range between 1 and 1000
- [ ] Set Quantity range between 1 and 1000
- [ ] Invalid String Pattern
- [ ] Product already exist

#### Flow: 
```mermaid
graph TD;
  A["Product Service: Update Existing Product"] --> B["Form Validation"]-- YES --> C{Business Validation}
  B -- NO --> G["Validation Exception"]
  C -- Yes --> D["Update Product"]
  C -- No --> F["Validation Exception"]
  D --> E[Product Price Service: Update Price]
  E --> H[Form Validation] -- Valid --> I{BusinesValidation}
  H -- Invalid --> J[Validation Exception]
  I -- Valid --> K[Update Price and date]
  I -- Invalid --> L[Validation Exception]
  K --> M[Product Updated Sucessfully]
```

### Feature 4: Delete Product

#### Pre-requisites:

- [ ] Product DAO (delete) 
- [ ] Product Service (delete)

#### Validations:

- [ ] Form Validation
		- id <= 0
		- is active = 0

- [ ] Business Validation
		- Check product id is present

#### Messages:
- [ ] Product Id cannot be zero or below zero
- [ ] Product not found

#### Flow: 
```mermaid
graph TD;
  A["Prodcuct Service: Delete Product"] --> B["Form Validation"]
  B -- Valid --> C{Business Validation}
  B -- Invalid --> G[Validation Exception]
  C -- Valid --> D[Delete Product]
  D --> E[Product Deleted]
  C -- Invalid --> F[Validation Exception]
```

## Module: Order

- [ ] Create Order table
- [ ] Create Order model

### Feature 1: Create Order

#### Pre-requisites:

- [ ] Order DAO (create) 
- [ ] Order Service (create)

#### Validations:

- [ ] Form Validation
	-Order null
	-address (null, empty, pattern)
	-quantity <=0
	-product id <=0
	-created by <= 0
	-total price <=0
	-quantity >= 10 

- [ ] Business Validation
		- Check Product Id is present
		- Check delivery time(Breakfast, lunch, dinner) is filled

#### Messages:
- [ ] Order cannot be null or empty
- [ ] Address cannot be null or empty
- [ ] Quantity cannot be zero or below zero
- [ ] Total Price cannot be zero or below zero
- [ ] User Id cannot be zero or below zero
- [ ] Product Id cannot be zero or below zero
- [ ] Quantity must be below 10
- [ ] Product not found
- [ ] User not found
- [ ] Invalid Address Pattern
		
#### Flow: 
```mermaid
graph TD;
  A["Order Service: Create or Place new Order"] --> B["Form Validation"] -- Valid --> C{Business Validation}
  B -- Invalid --> G[Validation Exception]
  C -- Valid --> D[Create Order]
  D --> E[Order Created]
  C -- Invalid --> F[Validation Exception]
```

### Feature 2:  List All Orders

#### Pre-requisites:
- [ ] Order Service(getAll)
- [ ] Order DAO(findAll)
		

#### Flow: 
```mermaid
graph TD;
  A["Order Service: GetAll Orders"] --> B[ FindAll Order]
  B -- Yes --> C["Return all Orders"]
  B -- No --> D["Return null"]
  ```
