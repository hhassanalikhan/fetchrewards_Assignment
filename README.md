# fetchrewards_Assignment

I have completed the assignment which baically consists of two activities. The flow of execution for this app i mentioned below,

1- Fitst, **splash screen** calls the GET API.
2- It then check if the previously saved text file's content is equal to the content of API by comparing the hash code of both the contents?
  - On the positive outcome of the above-mentioned condition, splash screen starts the new activity.
  - Otherwie, it rewrites the text file with the new content and updates that value of 'Hash' key of the SharedPreferences.
3- Then 'MainActivity' is started.
4- it uses the 'activity_main.xml' as the layout file which contains an expandable list.
5- It sets the list Adapter as "ExpandableListAdapter"
  - ExpandableListAdapter.java is a custom adaptr class for the explandable list.
6. 'MainActivity' then inflates the expandable list by using the following methods,
  1- Read the text file which contains the data of the API
  2- Conver text data in to the JSON Array
  3- Read every item in that array and create a **Hashmap**, <String, String>, with sorted list ids and sorted names.
  4- Use the hashmap as the content of expandable lists.
