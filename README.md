# fetchrewards_Assignment

I have completed the assignment which baically consists of two activities. The flow of execution for this app is mentioned below,

- Fitst, **splash screen** get the data from the API (using RequestQueue).

- It then checks if the previously saved API content in a text file is equal to the content of API by comparing the hash codes of both the strings?
  - On the positive outcome of the above-mentioned condition, the splash screen starts the new activity.
  - Otherwise, it rewrites the text file with the new content and updates that value of the 'Hash' key of **SharedPreferences**.
  - 
- Then '**MainActivity**' is started.

- It uses the 'activity_main.xml' as the layout file which contains an expandable list. I have also created 2 more layout files to format the new expandable list.

- It sets the list Adapter as "ExpandableListAdapter"
  - ExpandableListAdapter.java is a custom adapter class for the expandable list.
  
- '**MainActivity**' then inflates the expandable list by using the following steps,
  1- Read the text file which contains the data of the API
  2- Conver text data into a JSON Array
  3- Read every item in that array and create a **Hashmap**, <String, String>, with sorted list ids and sorted names.
  4- Use the hashmap as the content of expandable list's header and child elements.
