#coding=UTF-8
# run instruction : python TocHw3.py URL AREA ROADNAME YEAR
# ex			  : python TocHw3.py http://www.datagarage.io/api/5365dee31bc6e9d9463a0057 大安區 辛亥路 103
import sys
import json
import urllib2

page = urllib2.urlopen(sys.argv[1]).read()	## get web string
data = json.loads(page)
num = 0
total = 0
Area = unicode(sys.argv[2], 'utf-8')		##change to unicode because json.loads  , string is unicode
RoadName = unicode(sys.argv[3], 'utf-8')
Year = sys.argv[4]
for item in data:
	if item[u'鄉鎮市區'] == Area:
		if RoadName in item[u'土地區段位置或建物區門牌']:
			if int(item[u'交易年月'])/100 >= int(Year):
				total = total + int(item[u'總價元'])				
				num = num + 1
	#			print item[u'總價元']
	#			print item[u'土地區段位置或建物區門牌']
	#			print item[u'鄉鎮市區'] 
	#			print item[u'交易年月']
print int(total/num)
#print type(data) 

