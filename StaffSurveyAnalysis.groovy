
int count = 0
def cat1 = []
def cat1holder = []
def cat2 = []
def cat3 = []
new File("/Users/SPC/Downloads/_survey/").listFiles().each { file ->
	count++
	String question = ""
	int not_good = 0
	int poor = 0
	int fair = 0
	int good = 0
	int excellent = 0

	file.eachLine { line ->

		//if (line.contains("ICT")) println file.getName()

		String[] a = line.split(";")
		if (a.size() > 6){
			if (a[6] != null) question = a[6].replaceAll("\"", "").trim()
			String ans = a[9].replaceAll("\"", "").trim()
			if (ans.equals("Not at all (Strongly Disagree)")) not_good++
			if (ans.equals("Poor (Disagree)")) poor++
			if (ans.equals("Sufficient/Fair")) fair++
			if (ans.equals("Good (Agree)")) good++
			if (ans.equals("Excellent (Strongly Agree)")) excellent++
		}
	}
	//println file.getName() + ". " + question
	//println "Not Good - " + (not_good / 51) * 100
	//println "Poor - " + (poor / 51) * 100
	//println "Sufficent - " + (fair / 51) * 100
	//println "Good - " + (good / 51) * 100
	//println "Excellent - " + (excellent / 51) * 100
	//println " "

	if ((excellent / 51) + (good / 51) >= 0.6) {
		cat1 << question + ";" + (((excellent / 51) + (good / 51)) * 100)
		cat1holder << question
	}
	if ((excellent / 51) + (good / 51) + (fair / 51) >= 0.75) {
		if (!cat1holder.contains(question))
			cat2 << question + ";" + (((excellent / 51) + (good / 51) + (fair / 51)) * 100)
	}
	def xp = (poor / 51) + (not_good / 51)
	if (xp >= 0.25 && xp != 0) cat3 << question + ";" + (((poor / 51) + (not_good / 51)) * 100)
}

println "\r\nCategory 1;"
cat1.each {println it}
println "\r\nCategory 2;"
cat2.each {println it}
println "\r\nCategory 3;"
cat3.each {println it}
