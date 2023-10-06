# Contact Manager


## Prerequisites
This application is built in Java using JPA, H2 and Spring Boot. Please ensure you have JDK17+ installed before running.
Also, ensure you have Eclipse IDE to run the server.

## Running the Server
- Open the project by importing a maven project on Eclipse.
- Click on the `Run` button on the Eclipse IDE terminal to start the server.

## Testing Endpoints
- Open Postman, and first run the `Add Contact` request. In the Query Params, add contact info (without `""`)
- Run `Show Contacts` only after adding >= 1 contacts. Check here for contact IDs to use when deleting contacts.
- Run `Update Contacts` only after adding >= 1 contacts.
- Run `Delete Contact` only after adding a contact.

Note: If you run the latter endpoints before `Add` they will not kill the server, but you won't get anything meaningful from it apart from an error message.

## System
The system contains:
- Model Entities: Contact and Notes
- Repository Interfaces for the above.
- Service Classes for business logic involving contacts and notes
- Controller for Contacts to define endpoints for data access and manipulation.
- DTO classes for transferring data through different application levels.


[GPT Conversation](https://chat.openai.com/share/46a5c29f-3a1f-4417-8f7d-f6ac2f02bbd0)

I used GPT in this assessment to discuss tradeoffs on approaches, explanation of concepts I don't yet understand (since I don't have highly advanced knowledge in Java), and debugging some code in the end since I was running short on time. I did not use GPT for any other code generation. The screenshots are in the `img` folder.

