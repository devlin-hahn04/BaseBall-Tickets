import java.util.Scanner;



public class Menu {
    
    public static void main(String[] args){

        Scanner scanner= new Scanner(System.in);

        Stadium stadium= new Stadium();
        stadium.LoadFieldSeats();
        stadium.LoadMainSeats();
        stadium.LoadGrandStandSeats();

        System.out.println("                    Welcome To TicketOrder!\n");

        int input= -1;

        while(input != 4){

            System.out.println("Select Choice:");
            System.out.println("   1)Order Tickets");
            System.out.println("   2)Cancel Reservation");
            System.out.println("   3)Show Available Tickets");
            System.out.println("   4)Exit");
            
            boolean ValidInput= false;
    
            while(!ValidInput){
    
                try{
                    
                    input= scanner.nextInt();
    
                    if(input < 1 || input > 4){
    
                        throw new IllegalArgumentException("PLease Select between 1-4");
    
                    }
    
                    ValidInput= true; //exits loop if previous condition is false
                    
                } catch(java.util.InputMismatchException e){
                   
                    System.out.println("Please enter a number and from 1-4");
                    scanner.nextLine();
    
                }
    
    
            }

            System.out.println();
            

            //Ordering Tickets Menu
            if(input == 1){

                System.out.println("Choose Level:");
                System.out.println("   1)Field Level: $300, Available Seats: "+stadium.getFieldLevelSeatCount());
                System.out.println("   2)Main Level: $120, Available Seats: "+stadium.getMainLevelSeatCount());
                System.out.println("   3)GrandStand Level: $45, Available Seats: "+stadium.getGrandStandLevelSeatCount());
                
                boolean ValidInputLevel= false;
                int levelInput= -1;

                while(!ValidInputLevel){
    
                    try{
                        
                        levelInput= scanner.nextInt();
        
                        if(levelInput < 1 || levelInput > 3){
        
                            throw new IllegalArgumentException("PLease Select between 1-3");
        
                        }
        
                        ValidInputLevel= true; //exits loop if previous condition is false
                        
                    } catch(java.util.InputMismatchException e){
                       
                        System.out.println("Please enter a number and from 1-3");
                        scanner.nextLine();
        
                    }
        
        
                }
                
                System.out.println("Chose Level");
            }

            //Cancel Reservation Menu






            //Showing Available Tickets Menu
            

        }


        
        
        

    }





}
