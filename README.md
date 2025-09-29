#Introductory Application
This repository is for a course I am taking at North Dakota State University. The goal is to create an app that utilizes two activities and both implicit and explicit intents to traverse them. 

## How to get this running?
You will need:
Android Studio

Download this repository to your computer and open the outer project folder with android studio. At this point, make sure you have a device emulator created that you'd want to mirror this application on, or you could connect your phone given that its compatible. You should be able to build the app and test it out on a nmeulated device

## How To Use?
### Main Activity
This is where my name is displayed, as well as an ID number. 
There are also two buttons on this page, both take you to the second activity, although in a few different ways. The Start Activity Implicitly button starts the second activity using an implicit intent, conversely The Start Activity Explicitly button starts the second activity using an explicit intent.

### Second Activity
There is a list of challenges related to mobile software engineering listed here. Read up on these! They are very interesting and you should learn these.
The onther item on screen is a button that should take you back to the main activity (Explicitly).

## Approach to Project
I tried following the guide here to get a starting point, and I was able to do a lot, however I couldn't figure out how to insert buttons on the page. 

I pivoted towards making a new project from the empty views activity template. This allowed me to place buttons in the xml layout file for these activities. I also think they are more customizable and easier to format nicely compared to the ComponentActivites found in the tutorial.
The main activity had requirements spelled out for me, so I just added the text and the 2 buttons. It was weird to set up the implicit calls, android studio's Lint is highlighting that line as an error despite the project working with the implicit intent instantiated here.
The second activity was where I had some fun! All that was spelled out was that I had to display multiple challenges of mobile software development. I took this as a challenge learn something new. I did not want to hard code all of these values in the layout files, so I decided that I would use an Object-Oriented Approach.
The code is below, but I wanted to try to make these challenges more programatically. This was a huge help as changing the layout/organization/style of these challenges was much easier here than in other approaches. The toLinearLayout() method was how this was achieved.

    //Trying to do something new, rather than just make static content on the second activity
    private class MobileChallenge{
        private String name;
        private String description;

        public MobileChallenge(String name, String description){
            this.name = name;
            this.description = description;
        }

        public LinearLayout toLinearLayout(){
            TextView textView_name = new TextView(SecondaryActivity.this);
            textView_name.setText(name);
            textView_name.setTextSize(24);
            textView_name.setTextColor(Color.BLACK);
            textView_name.setTypeface(null, Typeface.BOLD);
            
            TextView textView_desc = new TextView(SecondaryActivity.this);
            textView_desc.setText(description);
            textView_desc.setTextColor(Color.GRAY);

            LinearLayout.LayoutParams name_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            name_params.setMargins(8,8,8,8);
            LinearLayout.LayoutParams desc_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            desc_params.setMargins(24,8,8,8);


            textView_name.setLayoutParams(name_params);
            textView_desc.setLayoutParams(desc_params);
            textView_name.setGravity(Gravity.CENTER_VERTICAL);
            textView_desc.setGravity(Gravity.CENTER_VERTICAL);


            LinearLayout ll = new LinearLayout(SecondaryActivity.this);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.setLayoutParams(name_params);

            ll.addView(textView_name);
            ll.addView(textView_desc);
            
            return ll;
        }

    }

