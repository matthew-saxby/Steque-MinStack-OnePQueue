**P02_DataTypes**



Author: Matthew Saxby
=====================



Implementations
===================


`Steque` Implementation
--------------------------

I started by creating nodes so that I could modify the Steque. I gave the steque different variables:
"first" "last" "size" and "modCount" so I could modify the Steque functions with these.
I created each function to do modify the Steque how it is supposed to, and throw the errors
that they are supposed to when they run into common errors. I created an Iterator called stequeIterator
that iterates through the Steque and feeds the functions the information they need to spit out the output
that they do. I then tested my Steque's performance by pushing a series of values and enqueueing and series of values.
I figured out what this should look like on paper, and when I printed it I got the same results. I tested this
with different sets of values and in different combinations of pushing and enqueueing. I also popped different
values, and printed out the Steque size as well. These all matched with what I had hoped they would be on paper.


`MinimumStack` Implementation
------------------------------


I started this by using a lot of the code that I used to implement the Steque data structure. Because of how similar
they are, I was able to use the same linked node structure and then simply change some of the functionality of this
data structure. I created a new function called minimum(), which can tell you the minimum value in the stack whenever.
I did this by creating a second, temporary stack, that whenever a value is added to the main stack, the function checks
if the top of the temporary stack is smaller than it. If it is smaller, then it adds this item to the top of the
temporary stack, and then pops off the previous item that was in this stack. Essentially the temporary stack
will always contain the smallest value that was inside the main stack, and it can always be compared to for each value,
so whenever minimumn() is called, it just returns the top of the minimum stack. I tested both strings and integers. My
implementation properly returns the size, minimum, and correctly pops and pushes new items on.


`OnePointerQueue` Implementation
-----------------------------------


The One Pointer Queue was the hardest of these three data structures for me to implement. It took a while for me
to figure out how I was actually going to do this, because I could not figure out how with only one pointer both
enqueueing and dequeueing would be possible. I talked to Dr. Denning who advised me to change the direction of which
the items in the queue point, so that way I would not have to ever access the node behind a current node, I could always
just use the next function. I had to think through the logic of the enqueue(), dequeue(), and next() and hasNext()
functions in my iterator. I had to change the logic for the next() and hasNext() because of the circular nature
of this queue, every item will always have a next, so Korban and I worked together to figure out how to create a counter
that will not allow a node to have a next if it has looped all the way back to the first item. I checked both strings
and integers, testing the enqueueing and dequeueing functions along with the size function, and it all seems to work
great. I have not run into any new bugs.



Memory Analysis
-----------------



class             | Memory Usage
------------------|----------
`Steque`          | $\sim ~40N $
`MinimumStack`    | $\sim ~40N $
`OnePointerQueue` | $\sim ~40N $

1 Node = 40 bytes * Number of Nodes


Known bugs / limitations
-------------------------


The biggest bug in my code was how the One Pointer Queue would just continue to point to itself over and over
and never stop printing. I finally figured out how to implement a counter into my iterator to stop this from
happening, but it was tricky because I am new to iterators and how to implement new things into them.


Reflection
===========

I received a lot of help from Korban Miller. We worked together on this, and Dr. Denning as well. We talked through
the different problems, and potential solutions for each. We drew out the different data structures, and how
they would work.


This is my own work, and while Korban and I worked together to brainstorm, this is all my code and my personal work.

The biggest problem that I encountered was an infinite loop on the OnePointerQueue. I had to figure out how to
stop it from looping, and Korban and I spent lots of time brainstorming, and I came up with the idea of a counter in the
iterator, and we implemented it in a similar way.








 

















