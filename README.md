<h1 align="center">My Food App</h1>

<p align="center">
  <img src="https://github.com/user-attachments/assets/10a07355-a399-4742-a18b-248e605e3498" width="400" alt="App food">
</p>

FoodieApp is a mobile application developed in Kotlin using Jetpack Compose for the user interface. The app allows users to register, log in, and explore various food products. It also has its own backend API developed in Python that handles product information and authentication functions. The app is available in both English and Spanish and follows the MVVM architecture.

## Backend Server

FoodieApp has its own backend server developed in Python using Flask. The server uses an SQLite database to manage and store data. It also has its own API that handles all product information and authentication functions, ensuring seamless interaction between the mobile app and the server. The server also supports image handling, allowing users to upload images, which are then saved with unique filenames and can be accessed via specific URL paths.

## Technologies Used

### Frontend (Android)

- **Language:** ![Kotlin](https://img.shields.io/badge/Kotlin-8b14f9.svg?style=for-the-badge&logo=openjdk&logoColor=white)
- **UI Framework:** ![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4.svg?style=for-the-badge&logo=jetpack-compose&logoColor=white)
- **Architecture:** ![MVVMN](https://img.shields.io/badge/MVVM-FFCA28.svg?style=for-the-badge&logo=android&logoColor=white) 
- **Navigation:** ![NavManager](https://img.shields.io/badge/NavManager-00ACC1.svg?style=for-the-badge&logo=android&logoColor=white)

### Backend (Server)

- **Language:** ![Python](https://img.shields.io/badge/Python-3776AB.svg?style=for-the-badge&logo=python&logoColor=white)
- **Framework:** ![Flask](https://img.shields.io/badge/Flask-000000.svg?style=for-the-badge&logo=flask&logoColor=white) 
- **API:** RESTful API for managing users and products
- **Image Hosting:** Own server

### Additional Libraries and Tools

- ![RetroFit](https://img.shields.io/badge/RetroFit-4DB33D.svg?style=for-the-badge&logo=android&logoColor=white)
- ![Hilt-Dagger](https://img.shields.io/badge/Hilt_Dagger-007396.svg?style=for-the-badge&logo=dagger&logoColor=white)
- ![Shared Preferences](https://img.shields.io/badge/Shared_Preferences-F9AB00.svg?style=for-the-badge&logo=android&logoColor=white)
- ![Scrum](https://img.shields.io/badge/Scrum-00BFFF.svg?style=for-the-badge&logo=scrum&logoColor=white)

## Visuals

<div align="center">
  <h3>Registration Screen</h3>
  <img src="https://github.com/user-attachments/assets/70ccc7f2-98f1-47c7-ae58-78a69de5ee01" width="300" alt="register">
</div>

<div align="center">
  <h3>Home (Eng-Spa)</h3>
  <table>
    <tr>
      <td align="center">
        <img src="https://github.com/user-attachments/assets/68e9f7af-78d5-428f-92ed-9879f059d420" width="300" alt="english" style="margin: 10px;">
      </td>
      <td align="center">
        <img src="https://github.com/user-attachments/assets/923d5490-514a-4664-a883-f9ec3b3de843" width="300" alt="products" style="margin: 10px;">
      </td>
      <td align="center">
        <img src="https://github.com/user-attachments/assets/1f9d96d1-f584-4b89-9806-41698e2ea1fc" width="300" alt="spanish" style="margin: 10px;">
      </td>
    </tr>
  </table>
</div>

<div align="center">
  <h3>Cart</h3>
  <table>
    <tr>
      <td align="center">
        <img src="https://github.com/user-attachments/assets/96f44540-d71c-459f-98d2-fbd714f51654" width="300" alt="empty" style="margin: 10px;">
      </td>
      <td align="center">
        <img src="https://github.com/user-attachments/assets/991992b2-a06c-480b-9dc0-9687d18274c5" width="300" alt="addToCart" style="margin: 10px;">
      </td>
      <td align="center">
        <img src="https://github.com/user-attachments/assets/d3664783-66fa-44bf-83f4-4f5b91cc5875" width="300" alt="cart" style="margin: 10px;">
      </td>
    </tr>
  </table>
</div>

<div align="center">
  <table>
    <tr>
      <td align="center">
        <h3>Product Detail</h3>
        <img src="https://github.com/user-attachments/assets/d391b5a8-daf2-4b3a-b70d-ea02f7edbcf3" width="300" alt="details">
      </td>
      <td align="center">
        <h3>Favorites</h3>
        <img src="https://github.com/user-attachments/assets/a9947b44-ab5f-4b27-a6a1-87d3eb55fbaa" width="300" alt="fav">
      </td>
      <td align="center">
        <h3>Profile</h3>
        <img src="https://github.com/user-attachments/assets/d575b36e-ae1d-4ab3-b898-feb39f93ae45" width="300" alt="profile">
      </td>
    </tr>
  </table>
</div>


## License
For open source projects, say how it is licensed.

## Project status
In Progress
