# ConcurrentDictionary

Practice library object for synchronized usage of Java Dictionary.

Time complexity of methods are listed in Dictionary.java.

ConcurrectDictionary uses both ArrayList and HashMaps for tracking data.
A map was used instead of a table since it doesn't come with any thread safety features, which
allows me to test my implementation for its own thread safety.
