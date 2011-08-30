import java.text.DecimalFormat


def sectionTitle = [:]
def b = [], c = [], d = [], e= [], f= [], g = []
String alpha, title
new File("/Users/SPC/Downloads/_survey/sections.txt").eachLine { line ->
	if (line.contains("->>")){
		alpha = line.substring(0, 1).trim()
		title = line.substring(4, line.length()).trim()
		sectionTitle[alpha] = title
	} else {
		String q = line.trim()
		if (!q.equals("")){
			if (alpha.equals("B")) b << q
			if (alpha.equals("C")) c << q
			if (alpha.equals("D")) d << q
			if (alpha.equals("E")) e << q
			if (alpha.equals("F")) f << q
			if (alpha.equals("G")) g << q
		}
	}
}

def answers = [
	"Not at all (Strongly Disagree)",
	"Not Good",
	"Poor (Disagree)",
	"Poor",
	"Sufficient/Fair",
	"Good (Agree)",
	"Good",
	"Excellent (Strongly Agree)",
	"Excellent",
	"Yes",
	"No"
]
def sectionList = [b, c, d, e, f, g]
int sectionCount = 0
sectionList.each { section ->

	String stitle = sectionTitle.keySet().toArray()[sectionCount] + ".) " + sectionTitle.getAt(sectionTitle.keySet().toArray()[sectionCount])
	println stitle

	int not_good = 0
	int poor = 0
	int fair = 0
	int good = 0
	int excellent = 0
	int count = 0

	section.each { q ->
		//println q
		new File("/Users/SPC/Downloads/_survey/").listFiles().each { file ->

			String question = ""
			file.eachLine { line ->
				String[] a = line.split(";")
				if (a.size() > 6){
					if (a[6] != null) question = a[6].replaceAll("\"", "").trim()
					if (q.equals(question)){

						String ans = a[9].replaceAll("\"", "").trim()
						if (!ans.trim().equals("")){
							if (answers.contains(ans)){
								count++
								if (ans.equals("Not at all (Strongly Disagree)") || ans.equals("Not Good")) not_good++
								if (ans.equals("Poor (Disagree)") || ans.equals("Poor")) poor++
								if (ans.equals("Sufficient/Fair")) fair++
								if (ans.equals("Good (Agree)") || ans.equals("Good")) good++
								if (ans.equals("Excellent (Strongly Agree)") || ans.equals("Excellent")) excellent++
								if (ans.equals("Yes")) excellent++
								if (ans.equals("No")) not_good++
							}

						}
					}
				}
			}
		}
	}
	//process for each section
	//println count
	DecimalFormat df = new DecimalFormat("#.##");
	def et = (excellent / count) * 100
	def gd = (good / count) * 100
	def fr = (fair / count) * 100
	def pr = (poor / count) * 100
	def ng = (not_good / count) * 100
	println "Excellent :\t\t" + df.format(et) + "%"
	println "Good :\t\t" + df.format(gd) + "%"
	println "Fair :\t\t" + df.format(fr) + "%"
	println "Poor :\t\t" + df.format(pr) + "%"
	println "Not Good :\t\t" + df.format(ng) + "%"
	println "Total :\t\t" + df.format((et + gd + fr + pr + ng)) + "%"
	println " "

	sectionCount++

	//make pie charts using jchart

}


