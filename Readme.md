**ChatApp-Backend**

Tools: Java, Spring Boot, MongoDB, AWS S3 API, Agora API, Docker, Git

This project was the creation of a backend server that supports a video call application. The system runs using the Spring Boot web framework and is coded in Java. Access to the server is secured using a JWT token system. Once access is granted the server provides multiple services. User accounts are stored as objects in a MongoDB non-relational database. This includes information such as name, username, password, and profile image retrieval data. Tokens are required in order to use the Agora video call API and are generated by the server. S3 bucket pre-signed URLs are also generated when requested so the frontend can access and upload profile images for the user. Future enhancements that I plan to work on are password encryption, online deployment, and text chat functionality.

![Login](https://github.com/jryther/jryther.github.io/blob/master/images/VideoApp/Login.png)

![VideoCall](https://github.com/jryther/jryther.github.io/blob/master/images/VideoApp/VideoCall.png)

![Profile Screen](https://github.com/jryther/jryther.github.io/blob/master/images/VideoApp/ProfileScreen.png)

![Edit Account](https://github.com/jryther/jryther.github.io/blob/master/images/VideoApp/EditAccount.png)
