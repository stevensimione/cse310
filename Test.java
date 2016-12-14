package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Date;
import resources.*;


public class Test {

	static ArrayList<Post> posts;
	static ArrayList<Group> groups;
	static ArrayList<User> users;
        static ArrayList<Boolean> subscribed;
	//use this class to test the methods in client
	public static void main(String []args)
	{
		System.out.println("Please type login and your userID to start.");
		Scanner scan = new Scanner(System.in); //scanner for variables
		String line = scan.nextLine();
		int defaultN = 5;
		String[] inputs;
		int userID;
		boolean inputID = false;
		inputs = line.split(" ");
		groups = new ArrayList<Group>();
		posts = new ArrayList<Post>();
		users = new ArrayList<User>();
                subscribed = new ArrayList<Boolean>();
		//filled properly
		fillGroups(3);
		fillPosts(4);
		while(!inputs[0].equals("login") || !inputID)
		{
			if(!inputs[0].equals("login"))
			{
				System.out.println("Please type login to start.");
				line = scan.nextLine();
				inputs = line.split(" ");
			}
			try{
				login(Integer.parseInt(inputs[1]));
				inputID = true;
			}
			catch(Exception e)
			{
				System.out.println("Please type a valid user ID.");
				line = scan.nextLine();
				inputs = line.split(" ");
			}
		}
		userID = Integer.parseInt(inputs[1]);
		System.out.println("Welcome, userid: " + userID + "\nPlease enter a command.");
		line = scan.nextLine();
		inputs = line.split(" ");
		while(!inputs[0].equals("logout"))
		{
			//parse the input
			inputs = line.split(" ");
			
			if(inputs[0].equals("ag"))
			{
				//ag [SPACE] or ag [nothing]
				try{
					if(inputs[1] == null){}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					ag(defaultN);
					System.out.println("Please enter a command.");
					line = scan.nextLine();
					inputs = line.split(" ");
					continue;
				}
				//ag something
				try{
					ag(Integer.parseInt(inputs[1]));
				}
				catch(Exception e)
				{
					System.out.println("Not a valid value for N.");
					help();
					System.out.println("Please enter a command.");
					line = scan.nextLine();
					inputs = line.split(" ");
				}
			}
			else if(inputs[0].equals("sg"))
			{
				//sg [SPACE] or sg [nothing]
				try{
					if(inputs[1] == null){}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					sg(defaultN);
					System.out.println("Please enter a command.");
					line = scan.nextLine();
					inputs = line.split(" ");
					continue;
				}
				//sg something
				try{
					sg(Integer.parseInt(inputs[1]));
				}
				catch(Exception e)
				{
					System.out.println("Not a valid value for N.");
					help();
					System.out.println("Please enter a command.");
					line = scan.nextLine();
					inputs = line.split(" ");
				}
			}
			else if(inputs[0].equals("rg"))
			{
				//rg [nothing] [nothing]
				try{
					if(inputs[1] == null){}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					System.out.println("Not a valid value for gname");
					help();
					System.out.println("Please enter a command.");
					line = scan.nextLine();
					inputs = line.split(" ");
					continue;
				}
				//rg gname [nothing]
				try{
					if(inputs[2] == null){}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					rg(inputs[1], defaultN);
					System.out.println("Please enter a command.");
					line = scan.nextLine();
					inputs = line.split(" ");
					continue;
				}
				//rg gname something
				try{
					rg(inputs[1], Integer.parseInt(inputs[2]));
				}
				catch(Exception e)
				{
					System.out.println("Not a valid value for N.");
					help();
					System.out.println("Please enter a command.");
					line = scan.nextLine();
					inputs = line.split(" ");
				}
			}
			else
			{
				System.out.println("Not a valid command.");
				help();
				System.out.println("Please enter a command.");
				line = scan.nextLine();
				inputs = line.split(" ");
			}
			System.out.println("Please enter a command.");
			line = scan.nextLine();
			inputs = line.split(" ");
		}
		scan.close();
	}

	/**
	 * Used to determine which discussion posts were read
	 * from the threads the user subscribed to.
	 * @param userID an ID formed as an integer for the application use
	 */
	public static void login(int userID)
	{
		System.out.println("login");
	}

	/**
	 * Prints list of supported commands and sub-commands. 
	 * Also prints the usage of the commands as well.
	 */

	public static void help()
	{
		//kinda basic, just using this as a test.
		System.out.print(  "help\n"
                                + "login - type 'login #' to login to your account, the number being your ID number\n"
				+ "ag [N] - type 'ag' to load groups 5 at a time, or type 'ag N', N being any number\n"
                                + "greater than 0, where you will load N groups at a time.\n"
                                + "     s - type 's n' to subscribe to one or more group. For example type 's 1 3; to\n"
                                + "     subscribe to group 1 and 3.\n"
                                + "     u - type 'u n' to unsubscribe to one or more group. For example type 'u 1 3; to\n"
                                + "     unsubscribe to group 1 and 3.\n"
                                + "     n - type 'n' to load more groups up the the N value given.\n"
                                + "     q - type 'q' to end the application.\n"
				+ "sg [N] - type 'sg' to load 5 groups at a time, or type 'sg N', N being any number\n"
                                + "greater than 0, where you will load N groups at a time. This will show subscribed\n"
                                + "subscribed groups only, and unread posts in each subscribed post.\n"
				+ "rg gname [N] - type 'rg gname' to read a group, gname being the name of the group\n"
                                + "and type 'rg gname N, to load N posts at a time. N being greater than 0. This\n"
                                + "method will show all messages in the group, with an N marking new messages.\n"
                                + "     [id] - a number between 1 to N. This will display a post of 1 to N.\n"
                                + "          n - would display at most N more lines of the post in [id].\n"
                                + "          q - would quit the post displayed in [id] and redisplay rg.\n"
                                + "     r - type 'r n' to mark one or more group read. For example type 'r 1-3 to mark\n"
                                + "     post 1 to 3 as read.\n"
                                + "     n - type 'n' to list the next N posts. If all posts are displayed, machine exits.\n"
                                + "     p - type 'p' to post to a group. To submit a post, type a '.' on its own line.\n"
                                + "     this will then load a new list of N posts, including the one submitted.\n"
                                + "     q - type 'q' to exit the rg command."
                                + "logout- type 'logout' to log out of your account\n"); //may need to change this
	}

	/**
	 * Logs out the user and terminates the client program.
	 */
	public static void logout()
	{
		System.out.println("logout");
	}

	/** 
	 * Lists all existing discussion groups, N groups at a time,
	 * from 1 to N. If N is not specified, a default value is 
	 * used in place.
	 * 
	 * Supports the following commands:
	 * s - subscribe to groups
	 * u - unsubscribe from groups
	 * n - list the next n discussion groups, if all groups displayed, 
	 * exit from mode.
	 * q - exits from the AG command.
	 * @param N integer representing how many groups to display
	 */
        
        private static void agPrintGroups(int lower, int upper){
            for (int i = lower; i < upper; i++){
                String sub = " ";
                if (subscribed.get(i) != null && subscribed.get(i)) //added global boolean variable.
                    sub = "s";
                System.out.println((i + 1) + ". (" + sub + ") " + groups.get(i).getGroupName());
            }
        }
        
        public static void updateSubscription(int n, boolean subscribe){
        if (subscribe){
            subscribed.add(n, new Boolean(true));
            subscribed.remove(n + 1);
        }else{
            subscribed.add(n, new Boolean(false));
            subscribed.remove(n + 1);
        }
        }
        //fake method to test out my code for ag.
        /*
        public static void testFakeGroup(){
        Group[] g = new Group[23];
        for (int i = 0; i < groups.size(); i++){
        int r = ((int)Math.random() * 100)%10;
        if (r > 8)
        subscribed.add(true);
        else
        subscribed.add(false);
        }
        }
        */ 
        
	public static void ag(int N)
	{
                if (N <= 0)
                    N = 5;
                int upper = N;
                int lower = 0;
                if (groups.size() < N)
                upper = groups.size(); 
             
            agPrintGroups(lower, upper);
                
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();
            String split = "";
            while(command.charAt(0) != 'q'){
            switch (command.charAt(0)){
                case 's': split = command.substring(1, command.length());
                          for (int i = 0; i < split.length(); i++){                              
                              String c = "";
                              int n = 0;
                              try{
                                 while (i < split.length() && split.charAt(i) != ' '){
                                  c += split.charAt(i) + "";
                                  i++;
                                 }
                                  if (!c.equals("")){
                                  n = Integer.parseInt(c);
                                  if (n <= 0 )
                                  System.out.println("You added an invalid number");
                                  
                              else
                                  updateSubscription(n - 1, true);
                                  }
                              }catch(Exception e){
                              System.out.println("There is an invalid character submitted");
                              }
                          }
                          agPrintGroups(lower, upper);      
                    break;
                case 'u':split = command.substring(1, command.length());
                          for (int i = 0; i < split.length(); i++){                              
                              String c = "";
                              int n = 0;
                              try{
                                 while (i < split.length() && split.charAt(i) != ' '){
                                  c += split.charAt(i) + "";
                                  i++;
                                 }
                                  if (!c.equals("")){
                                  n = Integer.parseInt(c);
                                  if (n <= 0 )
                                  System.out.println("You added an invalid number");
                                  
                              else
                                  updateSubscription(n - 1, false);
                                  }
                              }catch(Exception e){
                              System.out.println("There is an invalid character submitted");
                              }
                          }
                          agPrintGroups(lower, upper);      
                    break;
                case 'n':
                        if (groups.size() > upper){
                        upper += N;
                        lower += N;
                        if (upper > groups.size())
                            upper = groups.size();
                        agPrintGroups(lower, upper);
                        }else{
                        System.out.println("There are no more groups.");
                        }
                    break;
                case 'q':
                    break;
                default: System.out.println("You did not put in valid input");

                }
                            
                if (command.charAt(0) != 'q'){
                    input = new Scanner(System.in);
                    command = input.nextLine();
            }
            
            
	}
        }
	/** 
	 * Lists all subscribed groups, N groups at a time, from 1 to N. 
	 * The number of new posts in each group is shown before the group.
	 * @param N integer representing how many groups to display
	 */
	public static void sg(int N)
	{
		System.out.println("sg : " + N);
	}

	/** 
	 * Reads the groups and displays the status, time stamp, and 
	 * subject line of all posts in the group gname, N posts at
	 * a time. 
	 * 
	 * Supports the following commands:
	 * r - mark post as read, takes range of number as input.
	 * n - lists the next n posts
	 * p - post to the group.
	 * q - exit from rg commands
	 * When displaying content of a post, supports the following sub-commands
	 * n - display at most n more lines of the content
	 * q - quit displaying the post content. 
	 * @param gname The name of the group to be read
	 * @param N integer representing how many posts to be read
	 */
	public static void rg(String gname, int N)
	{
		//part 1, check if gname is in the target.
		boolean isGroup = false;
		Group targetGroup = null;
		for(Group g: groups)
		{
			if(gname.equals(g.getGroupName()))
			{
				isGroup = true;
				targetGroup = g;
			}
		}
		//if not, then exit, user can input a correct group.
		if(!isGroup)
		{
			System.out.println("Group " + gname + "is not in the list of groups. Please try again.");
			return;
		}
		//if it is, then we run through input. 
		else
		{
			//part 2: get the posts from the group
			ArrayList<Post> posts = targetGroup.getPosts();
			class PostComparator implements Comparator<Post>//comparator to sort the posts by if it's new and date.
			{
				@Override
				// -1 = arg1 > arg0, 0 = arg1 = arg0, 1 = arg1 < arg0
				public int compare(Post arg0, Post arg1) { 
					// TODO Auto-generated method stub
					if(arg0.getIsNew()&& arg1.getIsNew())
					{
						if(arg0.getTimeStamp()>arg1.getTimeStamp()) 
							return -1;
						else if(arg0.getTimeStamp()<arg1.getTimeStamp())
							return 1;
						else if(arg0.getTimeStamp()==arg1.getTimeStamp())
							return 0;
						return -1; //if otherwise? shouldn't be the case
					}
					else if(!arg0.getIsNew()&&arg1.getIsNew())
						return 1;
					else if(arg0.getIsNew()&&!arg1.getIsNew())
						return -1;
					else
					{
						if(arg0.getTimeStamp()>arg1.getTimeStamp())
							return -1;
						else if(arg0.getTimeStamp()<arg1.getTimeStamp())
							return 1;
						else if(arg0.getTimeStamp()==arg1.getTimeStamp())
							return 0;
						return -1; //if otherwise? shouldn't be the case
					}
				}
			}
			Collections.sort(posts, new PostComparator()); //sort posts by new and timestamp.
			//list the posts in order using ID.
			int numberOfPosts = 0;
			for(int i = 0; i < posts.size(); i ++)
				posts.get(i).setPostID(i+1);
			for(int i = 0; i < N; i ++){
				try{
					printPost(posts.get(i));
					numberOfPosts++;
				}
				catch(IndexOutOfBoundsException e)
				{
					break; // we've gone too far, there's no more posts to print
				}
			}
			//part 3: command line functionality
			Scanner s = new Scanner(System.in);
			System.out.println("Please type a command.");
			String input = s.nextLine();
			String[] arguments = input.split("\\s+|-"); //splits by spaces and dash.
			boolean quitRG = false;
			boolean quitID = false;
			while(!quitRG)
			{
				int id = 0;
				//try ID command, also tests if it's blank
				try{
					id = Integer.parseInt(arguments[0]);
				}
				catch(NumberFormatException e)
				{
					//next command, might be part of some other sub command.
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					//command is blank.
					System.out.println("Please type a command.");
					input = s.nextLine();
					arguments = input.split("\\s+|-"); //splits by spaces and dash.
				}
				if(id > 0 && id < posts.size()) //if ID is within bounds.
				{
					//we run sub commands within ID.
					Post p = posts.get(id-1);
					String[] postLines = p.getBody().split("\n");
					int linesPosted = 0;
					boolean broke = false;
					for(int i = 0; i < N; i++)
					{
						try{
							System.out.println(postLines[i] + "\n");
							linesPosted++;
						}
						catch(IndexOutOfBoundsException e)
						{
							broke = true;
							break; // we've gone too far, there's no more posts to print
						}
					}
					while(!quitID&&!broke)
					{
						System.out.println("Please type a command.");
						input = s.nextLine();
						if(input.equals("n"))
						{
							for(int i = linesPosted; i < N+linesPosted; i++)
							{
								try{
									System.out.println(postLines[i] + "\n");
									linesPosted++;
								}
								catch(IndexOutOfBoundsException e)
								{
									broke = true;
									break; // we've gone too far, there's no more posts to print
								}
							}
						}
						else if(input.equals("q"))
						{
							quitID = true;
						}
						//display post - n lines of content
						else
						{
							System.out.println("Invalid command. Please type a command.");
							input = s.nextLine();
							arguments = input.split("\\s+|-"); //splits by spaces and dash.
						}
					}
					quitID = false;
					p.setIsNew(false);
					Collections.sort(posts, new PostComparator()); //sort posts by new and timestamp. 
					//must also reset post ID
					for(int i = 0; i < posts.size(); i ++)
							posts.get(i).setPostID(i+1);
					for(int i = 0; i < N; i ++){
						try{
							printPost(posts.get(i));
						}
						catch(IndexOutOfBoundsException e)
						{
							break; // we've gone too far, there's no more posts to print
						}
					}
				}
				//mark posts as read. r 1 = mark 1 as read, r 1-3 = mark 1-3 as read.
				else if(arguments[0].equals("r"))
				{
					int rLocation = 0;
					boolean parsedEndLocation = false;
					int rEndLocation = 0;
					//r 1
					try
					{
						rLocation = Integer.parseInt(arguments[1]);
						if(arguments[2] == null){} //we can't do anything here, an exception will be thrown.
						else
						{
							parsedEndLocation = true;
							rEndLocation = Integer.parseInt(arguments[2]);
							if(rLocation > 0 && rLocation <= N //rLoc and rEndLoc bounds is from (0, n]
							&& rEndLocation > 0 && rEndLocation <=N 
							&& rEndLocation > rLocation) //rEndLoc > rLoc always, otherwise, it fails.
							{
								//1-3 0, 
								for(int i = rLocation; i <= rEndLocation; i ++)
									posts.get(i-1).setIsNew(false);
							}
							else
							{
								System.out.println("Invalid argument(s) for location. Please enter another command.");
								input = s.nextLine();
								arguments = input.split("\\s+|-"); //splits by spaces and dash.
							}
						}
					}
					// r x | r 1-x | r x-1
					catch(NumberFormatException e)
					{
						System.out.println("Invalid argument(s) for location. Please enter another command.");
						input = s.nextLine();
						arguments = input.split("\\s+|-"); //splits by spaces and dash.
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
						if(!parsedEndLocation)
							//must be at 1. Since we didn't run into NFE, then we can continue with rLocation here.
							posts.get(rLocation-1).setIsNew(false);
					}
					Collections.sort(posts, new PostComparator()); //sort posts by new and timestamp.
					//must also reset post ID
					for(int i = 0; i < posts.size(); i ++)
							posts.get(i).setPostID(i+1);
					for(int i = 0; i < N; i ++){
						try{
							printPost(posts.get(i));
						}
						catch(IndexOutOfBoundsException e)
						{
							break; // we've gone too far, there's no more posts to print
						}
					}
				}
				else if(arguments[0].equals("n"))
				{
					//display next n posts
					if(numberOfPosts == posts.size())
					{
						System.out.println("Exceeded the number of posts. Exiting command.");
						quitRG = true;
						continue;
					}
					Collections.sort(posts, new PostComparator()); //sort posts by new and timestamp.
					//must also reset post ID
					for(int i = 0; i < posts.size(); i ++)
							posts.get(i).setPostID(i+1);
					for(int i = numberOfPosts; i < N+numberOfPosts; i ++){
						try{
							printPost(posts.get(i));
							numberOfPosts++;
						}
						catch(IndexOutOfBoundsException e)
						{
							break; // we've gone too far, there's no more posts to print
						}
					}
				}
				else if(arguments[0].equals("p"))
				{
					//post to the group
					System.out.println("Initiating post write. Please write a post. Once finished, type newline, then \\q.");
					String post = "";
					String endSequence = "\n\\q"; // (newline)\q = 3 characters.
					String postSubstring = ""; //substrings the last 3 characters to get \n\\q.
					String title = "";
					Post p = new Post();
					Date d = new Date();
					while(!endSequence.equals(postSubstring))
					{
						input = s.nextLine();
						if(input.equals(""))
							post+="\n";
						else
							post += input;
						try
						{
							postSubstring = post.substring(post.length()-3, post.length());
						}
						catch(Exception e)
						{
							// out of bounds here, continue anyway? the user didn't input anything.
							if(input.compareTo("")>0)
							{
								input = s.nextLine();
								post += input;
							}
							else
							{
								//quit 
								System.out.println("Empty string for post. Please type a new line, then \\q to exit.");
								continue;
							}
						}
					}
					System.out.println("Please title the post.");
					title = s.nextLine();
					p.setBody(post);
					p.setTimestamp(d.getTime());
					p.setAuthor("current username");//placeholder for name.
					p.setPostID(1);
					p.setIsNew(true);
					p.setSubject(title);
					posts.add(0, p);
					numberOfPosts = 0;
					Collections.sort(posts, new PostComparator()); //sort posts by new and timestamp.
					//must also reset post ID
					for(int i = 0; i < posts.size(); i ++)
							posts.get(i).setPostID(i+1);
					for(int i = 0; i < N; i ++){
						try{
							printPost(posts.get(i));
							numberOfPosts++;
						}
						catch(IndexOutOfBoundsException e)
						{
							break; // we've gone too far, there's no more posts to print
						}
					}
				}
				//quit
				else if(arguments[0].equals("q"))
				{
					quitRG = true;
				}
				else
				{
					//anything else, prompt.
					System.out.println("Invalid argument. Please enter another command.");
					input = s.nextLine();
					arguments = input.split("\\s+|-"); //splits by spaces and dash.
				}
				if(quitRG == false)
				{
					System.out.println("Please type a command.");
					input = s.nextLine();
					arguments = input.split("\\s+|-"); //splits by spaces and dash.
				}
			}
		}
	}
	//filling the groups with names. ngroups = 3, change it if necessary.
	public static void fillGroups(int nGroups)
	{
		String[] groupNames = {"Python", "Java", "C"};
		
		for(int i = 0; i < nGroups; i ++)
		{
			ArrayList<Post> p = new ArrayList<Post>();
			Group g = new Group(i, groupNames[i], p);
			groups.add(i, g);
		}
	}
	//filling the posts up. nposts = 4, change it if necessary.
	public static void fillPosts(int nPosts)
	{
		String[] pTitles= {
			"RTFM Python",
			"How do tabs work in Python?",
			"Why is this not working?!? HELP?",
			"Please finish this assignment for me"
		};
		String[] jTitles= {
			"RTFM Java",
			"How do tabs work in Java?",
			"y is dis not work",
			"thanks for finishing my assignment!"
		};
		String[] cTitles = 
		{
			"RTFM C",
			"Size is 0 bytes after 8 bytes was allocated",
			"seg fuatl!?!?",
			"my stack is getting smashed XD"
		};
		String[] pBodies = 
		{
			"So I tried to work on \nsomething in Python \nand somebody told me to RTFM. \nWhat does this mean?",
			"What are indentations and what \ndo they have to \ndo with python syntax?",
			"I tried to do a for loop in python and only deleted one tab, why doesn't this work?",
			"help pls my assignmen is due tomorrow why is my code wrong"
		};
		String[] jBodies =
		{
			"What is RTFM in java?",
			"So I tried writing code in one line and was wondering if there's an easy way to understand what I'm doing",
			"pplz hlep i cannot figure out what my code dooes",
			"hey stackezzzzchange wazzup thanks for finishing my networkign project bruhs"
		};
		String[] cBodies =
		{
			"how does one RTFM in C?",
			"I keep getting this error in valgrind, what does this mean?",
			"wat is sge falut?",
			"heyyyy c is telling my my stack is super smashed ;)"
		};
		//unix timestamps, obtained from http://www.onlineconversion.com/unix_time.htm
		long [] timestamps = {
			1476493323, 1476583384, 1476673445, 1476763506, //Python is in October
			1479531967, 1479712089, 1479802150, 1479719411, //Java is in November
			1480759872, 1480849933, 1480939994, 1481030055  //C is in December
		};
		//multiply timestamps by 1000 because Date requires it in ms.
		for(int i = 0; i < timestamps.length; i ++)
			timestamps[i]*=1000;
		for(int i = 0; i < groups.size(); i ++)
		{
			for(int j = 0; j < nPosts; j++)
			{
				ArrayList<Post> p = groups.get(i).getPosts();
				Post post = new Post();
				if(i == 0) //python
				{
					post.setSubject(pTitles[j]);
					post.setBody(pBodies[j]);
					post.setTimestamp(timestamps[j]);
					if(j==0 || j==1)
						post.setIsNew(false);
					p.add(j, post);
				}
				if(i == 1) //java
				{
					post = new Post();
					post.setSubject(jTitles[j]);
					post.setBody(jBodies[j]);
					post.setTimestamp(timestamps[j+4]);
					if(j==0 || j==1)
						post.setIsNew(false);
					p.add(j, post);
				}
				if(i == 2)//c
				{
					post = new Post();
					post.setSubject(cTitles[j]);
					post.setBody(cBodies[j]);
					post.setTimestamp(timestamps[j+8]);
					if(j==0 || j==1)
						post.setIsNew(false);
					p.add(j, post);
				}
			}
		}
	}
	public static void printPost(Post p)
	{
		String n = " ";
		Date d = new Date(p.getTimeStamp());
		String date = d.toString();
		date = date.substring(0, date.length()-5);
		if(p.getIsNew())
			n = "N";
		System.out.println(p.getPostID()+ ". " + n + "  " + date + "  " + p.getSubject());
	}
	
	public static void printContent(String groupName, Post p)
	{
		Date d = new Date(p.getTimeStamp());
		String date = d.toString();
		System.out.println("Group: " + groupName + "\n"
						+  "Subject: " + p.getSubject() + "\n"
						+  "Author: " + p.getAuthor() + "\n"
						+  "Date: " + date + "\n\n"
						+  p.getBody());
	}
}
