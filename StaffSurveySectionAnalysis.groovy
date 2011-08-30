
int count = 0

new File("/Users/SPC/Downloads/_survey/").listFiles().each { file ->
	count++
	String question = ""
	int not_good = 0
	int poor = 0
	int fair = 0
	int good = 0
	int excellent = 0

	file.eachLine { line ->
		String[] a = line.split(";")
		if (a.size() > 6){
			if (a[6] != null) question = a[6].replaceAll("\"", "").trim()

			if (question.startsWith("Please rate how effective")) println question

			String ans = a[9].replaceAll("\"", "").trim()
			if (ans.equals("Not at all (Strongly Disagree)")) not_good++
			if (ans.equals("Poor (Disagree)")) poor++
			if (ans.equals("Sufficient/Fair")) fair++
			if (ans.equals("Good (Agree)")) good++
			if (ans.equals("Excellent (Strongly Agree)")) excellent++
		}
	}
	//println question
	//println file.getName() + ". " + question
	//println "Not Good - " + (not_good / 51) * 100
	//println "Poor - " + (poor / 51) * 100
	//println "Sufficent - " + (fair / 51) * 100
	//println "Good - " + (good / 51) * 100
	//println "Excellent - " + (excellent / 51) * 100
	//println " "


}

