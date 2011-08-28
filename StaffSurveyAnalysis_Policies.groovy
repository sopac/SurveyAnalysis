//Please rate how familiar you are with SPC's policies on the following:

int count = 0

Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>() // q, <not_good, poor, fair, good, excellent>

new File("/Users//SPC/Downloads/_survey/jquarks4s_answers_policies.csv").eachLine { line ->
	String[] arr =  line.split(";")
	if (count != 0){
		for (int i = 12; i <= 75; i = i + 8){
			String q = arr[i].replaceAll("\"", "").trim()
			String ans = arr[i - 3].replaceAll("\"", "").trim()

			if (!map.keySet().contains(q)) {
				def l = []
				l << 0
				l << 0
				l << 0
				l << 0
				l << 0
				map.put(q, l)
			} else {
				def l = map.get(q)

				if (ans.equals("Not Good")) l.set(0, l.get(0) + 1)
				if (ans.equals("Poor")) l.set(1, l.get(1) + 1)
				if (ans.equals("Sufficient/Fair")) l.set(2, l.get(2) + 1)
				if (ans.equals("Good")) l.set(3, l.get(3) + 1)
				if (ans.equals("Excellent")) l.set(4, l.get(4) + 1)

				map.put(q,l)
			}
		}
	}
	count++
}
println map

def cat1 = []
def cat1_holder = []
def cat2 = []
def cat3 = []

map.keySet().each { question ->

	def l = map.get(question)
	def not_good = (l.get(0) / 51) * 100
	def poor = (l.get(1) / 51) * 100
	def fair = (l.get(2) / 51) * 100
	def good = (l.get(3) / 51) * 100
	def excellent = (l.get(4) / 51) * 100

	//println excellent

	if (excellent + good >= 60) {
		cat1 << question + " ; " + (excellent + good)
		cat1_holder << question
	}
	if (excellent + good + fair >= 75) {
		if (!cat1_holder.contains(question))
			cat2 << question + " ; " + (excellent + good + fair)
	}
	if (poor + not_good >= 25 ) cat3 << question + " ; " + (poor + not_good)

}

println "\r\nCategory 1;"
cat1.each {println it}
println "\r\nCategory 2;"
cat2.each {println it}
println "\r\nCategory 3;"
cat3.each {println it}