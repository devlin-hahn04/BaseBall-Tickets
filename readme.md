Project 1: Ticket Reservation System:

This project is part of the Data Structures Course and involves implementing a ticket reservation system for a baseball stadium. The program is designed to manage seat availability, customer reservations, and waitlists for sections that are fully booked. The users will use the system to handle client interactions and manage reservations effectively.


Project Description:
    The baseball stadium offers three seating sections, each with unique pricing and capacities:
        Field Level:
            Premium seats located behind home plate and along the baselines.
            Cost: $300 per seat
            Capacity: 500 seats
        Main Level:
            Seats directly above the field level, offering great views behind the dugouts and along the baselines.
            Cost: $120 per seat
            Capacity: 1,000 seats
        Grandstand Level:
            Seats on the upper deck, behind home plate, and along the baselines.
            Cost: $45 per seat
            Capacity: 2,000 seats

The system allows for the following functionalities:
    Seat Reservations:
        Operators collect customer details (name, email, and phone number) and check seat availability. If seats are available, the system calculates the total cost and reserves the requested seats.
    Waitlist Management:
        If a section is fully booked, the operator can add customers to a waitlist. The waitlist is managed using a Queue, ensuring that the customers are served in the order they are added.
    Reservation Cancellation:
        Operators can cancel reservations, freeing up seats for customers on the waitlist or future reservations by adding them back to the respective sections. This functionality uses a Stack to manage the history of reservations, allowing operators to undo the last reservation or cancellation action if necessary.

    Seat Availability:
        Operators can view the current availability of seats across all sections, helping them monitor which sections are fully booked and which have available seats.




Implementation Details
    Class Design:
        Client Class:Represents customers with attributes such as name, email, and phone number. The class stores the client's information along with the number of seats they wish to reserve. If the reservation cannot be fulfilled, clients are added to a waitlist.

        Seat Class: Represents individual seats with attributes like section, row, and seat number. The class is used to model a specific seat in the stadium and is central to managing seat assignments, cancellations, and availability.

        Stadium Class: Manages all seat reservations, availability, and waitlists. It handles seat assignments, seat availability updates, waitlist management, and reservation cancellations.
    
    Data Structures Used:
        Set: The Set data structure is used to store available seats, ensuring that seat availability is managed efficiently and that no duplicates exist.

        LinkedList: TheLinkedList is used to track the reservation history and transaction logs. It keeps a record of past reservations, which allows operators to undo the most recent actions (reservation or cancellation) using the Stack.
        
        HashMap: The Hashmap maps customers to their reserved seats, allowing for easy lookup and efficient management of reservations.
        
        Stack: The Stack data structure is used for implementing undo functionality. By storing the history of seat reservations and cancellations in the stack, operators can reverse the most recent action if needed.
        
        Queue: The Queue is used to maintain the waitlist for fully booked sections. When a customer is added to the waitlist, they are placed in the queue, and the system processes them in the order they are added, offering them seats as they become available.

    Key Methods:
        WaitList Method: This method allows an operator to add a client to the waitlist if there are no available seats in the selected section. It asks for the client’s name, email, phone number, and the number of seats they wish to reserve. The client is added to the corresponding section’s waitlist (Field, Main, or Grandstand Level) based on their preference.

        Cancel Method: This method allows an operator to cancel a reservation, freeing up seats for other customers. When a reservation is canceled, the seats are returned to the corresponding section’s pool of available seats. If there are customers on the waitlist, they are assigned available seats in the order they appear in the queue. This process continues until all available seats are allocated or the queue is empty.

        Reservation History and Undo: The system uses a Stack to track the reservation history, enabling the operator to undo the most recent reservation or cancellation action. This ensures that the operator can reverse a mistake or change their mind.
        
    Input and Output:
        Operators interact with the program via a menu. The menu allows operators to select various options, such as ordering tickets, canceling reservations, checking seat availability, and managing the waitlist.

        Outputs include confirmation messages for successful reservations, cancellation updates, the current waitlist status, and available seat summaries. If a reservation or cancellation is successfully processed, the system displays feedback to the operator, confirming the action.

    Results:
	    The Ticket Reservation System provides clear outputs at each stage of the reservation process, guiding the operator through seat reservations, cancellations, and the management of waitlists. Below are the types of results the operator can expect:
	        1. Reservation Confirmation
                When a reservation is successfully made, the system will display a confirmation message showing:
                    The client's name, email, and phone number.
                    The total cost of the reservation.

            2. Waitlist Management
                If a section is fully booked, the operator will be prompted to add the client to the waitlist.

	        3. Reservation Cancellation
                When a reservation is canceled, the system will:
                    a)Free up the seats, making them available again for future reservations or for clients on the waitlist.
                    b)Notify the operator of the successful cancellation.
                    c)If seats are available from the cancellation, the system will offer those seats to clients on the waitlist, one by one, in the order they were added. The system will show the following:
                        Whether the client's seat request was fully fulfilled or partially fulfilled.

	        4. View Available Seats
                The system will allow the operator to view the current seat availability across all sections. The output will display:
                    The total number of available seats in each section.
                    
	        5. Invalid Input Handling
                If an operator enters invalid data (such as an invalid seat number or negative number of seats), the system will display an error message and prompt for correct input.











Evaluations:
Evaluation- Devlin’s Team members and Self
	
Yediel Acosta-
	a)Contribution of member to the group:		
        Member was very helpful throughout the project by giving feedback on certain          implementations like the order ticket method and class structures and the overall organization of the program. Good communication when needed and ambition to designate themselves tasks.

	b)Communication to group:
        As mentioned before, team member always had good communication with the team on what tasks they were working on, any difficulties they confronted, ideas for certain implementations and frequent updates on anything related to the project.

	Overall Evaluation- 10/10

Diego Rios-
	a)Contribution of member to the group:
        Member had been keen on the project from the beginning and wanted to begin working on it as quickly as possible to be ahead of their workload. He helped out throughout  the project with the overall look of the program to the user and showing users available tiickets, along with some help here and there with other tasks other members were working on.

	b)Communication to group:
        Member had good communication with his team members and updated them frequently on their progress in their tasks. Was always available to communicate and discuss any detail about the project.

	Overall Evaluation- 10/10

Devlin Hahn-

	a)Contribution of member to the group:
        I was always very involved in the project, constantly making time to fit in coding sessions for the project and auto-designated lots of tasks when team members were a bit busy with their classes. Heavy involvement in the cancel method functionality, data validation and keeping group on track with tasks that needed to be completed.

	b)Communication to group:
        I kept very good communication with my team members, keeping them always posted on what needed to be done and what was completed, what small bugs, if any, I had found, and helping with any difficulty they had with their tasks through texts.

		
    Overall Evaluation- 10/10



Evaluation - Diego’s Team Members and self:

Devlin Hahn-
    a)Contribution of member to the group:
        Member was a great teammate who worked hard and helped keep the group organized. They implemented the first choice, laid out the base code for the project, and made sure everything was running smoothly. Their involvement in every part of the process really helped us stay on track.
    b)Communication to group:
        Member communicated really well with the team, keeping everyone updated and making sure we all stayed on the same page. Their openness made it easy to share ideas and solve problems together.
	Overall Evaluation- 10/10

Yediel Acosta- 
    a)Contribution of member to the group:
        The member brought fantastic ideas to the table and played a key role in shaping the project. They provided insightful feedback on aspects such as the order ticket method, class structures, and overall organization. Their strong communication skills and proactive approach to taking on tasks greatly contributed to the team's success.
    b)Communication to the group:
        The member communicated well throughout the project, keeping everyone on the same page and contributing to a smooth workflow.
	Overall Evaluation- 10/10

Diego Rios-
	a) Contribution of member to the group:
        I contributed by implementing some functions and taking care of choice 3. I was involved in some of the key parts of the project, often giving insight on the ideas I had to solve some of the problems. I also strived to have a good attitude and to help keep things moving forward.
	b) Communication to the group: 
        I communicated clearly with the team, ensuring everyone was on the same page. I was responsive when needed and made sure to clarify any doubts, helping the project stay organized.
    Overall Evaluation- 10/10


Evaluation: Yediel’s Team Members and Self
Devlin Hahn -
    a)Contribution of member to the group:
        He was an amazing and very passionate member of the group, who helped in doing the base of the project and ensured all worked as intended. He mainly took care of the core as Diego and I helped him in ideas and designs. Overall, he was a fantastic member who was a core part in the project as a whole.
    b)Communication to the group:
        He also kept us updated on major stuff, whether it was new stuff he implemented or questions about how to manage the project. We had great communication helping each other out, as a group project intends to.
    Overall: 10/10

Diego Rios -
    a)Contribution of member to the group:
        He was incredibly helpful in the project as well as he focused on the implementation of some functions that ended up of big importance in the project. He was a great asset to the group, much like Devlin, which helped in the construction of the code
    b)Communication to the group:
        He kept updated the team members of his collaborations whenever he made them alongside being punctual about his presence in the work. Between us three, we communicated with him and him with us, whenever any of us had doubts
    Overall: 10/10
Yediel Acosta (me) -
    a)Contribution of member to the group:
        I was often doing my best in helping around my teammates in each question or tip they searched for with me. I focused on giving advice to the team, helping brainstorming ideas with the team for a better implementation, ensuring everything ran well, and clarified the project at the end. I believe I was a good asset to the team, as they were with me
    b)Communication to the group:
        I always verified with my team members if they needed any help and offered myself whenever they needed. Additionally, I also was accessible for whenever a douth rose, willing to help and answering as fast as I could. In short, I believe I was there for when they needed me
    Overall: 10/10

