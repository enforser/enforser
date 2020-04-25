(ns blog.pages.posts.learning-functional-first)

(def title "Functional First")

(defn content
  [context]
  [:div {:class "container"}
   [:div {:class "row"}
    [:h3 title]]
   [:div {:class "row"}
    [:p "In 2014 I began studying Computer Science at Carleton univerity in Ottawa, Canada. I had some exposure to programming at high school previous to university, but nothing beyond a little bit of PHP, Perl, JavaScript, and HTML. The first year curriculum included learning programming basics (variables, loops, functions, etc), followed by learning the basics of C and Java (pointers, memory, imperative vs. object oriented, etc). The second year went more in depth on certain topics within Computer Science. We learnt about operating systems, data structures, time complexity, and expanded more on how to write Object Oriented applications. Up until the beginning of my third year at school, I'd never heard of functional programming, LISP, Haskell, Clojure; none of the things that I am most interested in now."]
    [:p "My introduction to functional programming began with a course called Programming Paradigms, where we spent half of the semester learning the fundamentals of programming in Scheme. I found this course really enjoyable, but didn't absorb the true power of what we were learning until the following semester, when I took 8 months off school for a work term at a place that had about 70-80 developers - all of them writing Clojure."]
    [:p "For me, spending that time learning Clojure was a pivotal turning point in how I saw and interpreted problems and their solutions compared to how I'd been taught in school. Immutability by default, treating everything as data, and REPL driven development became norms for me. After 8 months of working full time, I continued to work professionally in Clojure with slightly reduced hours and resumed school."]
    [:p "Starting my classes again, I realized how much I'd learned from working in Clojure - and also how much my functional programming revelations didn't align with the manner in which my coursework was designed. Courses and projects were designed around Object Oriented programming. Every course seemed to use an object oriented language such as Java, Smalltalk, or C++. Coding examples utilized imperative style loops instead of recursion, reducers, or even iterators such as map. When a project had prewritten boilerplate code, it always implemented object oriented attributes and design. This was a major drag for me, as I now knew there were greener pastures out there, but the more dissapointing realization was that everybody in my classes was learning the object oriented way - and I was one of very few who had exposure to the joys of functional programming. The computer science undergraduate course work tended to place object oriented on a pedestal. It taught object oriented as if there were no other realistic alternatives."]
    [:p "While Object Oriented is certainly the most widely used programming paradigm, Functional Programming allows you to write code that reduces the occurrence of bugs, makes it easier to debug when they do appear, encourages writing code that is easier to refactor, and makes understanding your code easier for yourself and others. I think it is a tragedy that functional styled programming is not the norm in undergraduate course work. Learning object oriented by default may appeal more widely to recruiters who use Object Oriented as a search word on LinkedIn, but in practice thinking in a functional way will go much further in enabling students to actually become more adept programmers. Most modern languages expose enough functional concepts to be able to pick them up regardless of if you are coming from a functional or imperative background, so I don't think someone who is more comfortable with functional programming than object oriented is at a disadvantage when it comes to being able to apply their skills in a real setting. If anything, I think object oriented is far easier to pick up if you already have a strong basis in functional programming than the other way around. This is because functional programming encourages more abstraction away from implementation details. For example, recursion abstracts away mutable state that is being transformed when looping over data. If you understand these abstractions then you can implement them in any language, allowing you to shift your focus to the larger problem at hand."]
    [:p "In my mind, there is little to lose and lots to gain from starting off in the functional paradigm. It encourages good software development practices, and abstracts away problems such as state and mutability. Due to their added complexity, imperative and object oriented programming should be tools that we reach for only when they are needed. Having functional programming as the default paradigm encourages a more simple approach to complex problems, and that is why it should replace object oriented as the primary paradigm that is learnt by software engineers today."]]])
