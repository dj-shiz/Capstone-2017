from django.shortcuts import render
from django.contrib.auth.decorators import login_required
from django_tables2 import RequestConfig
from reporter.models import IncidentLog
from reporter.tables import LogTable
from reporter.filters import LogFilter
from reporter.forms import IncidentForm
from django.shortcuts import redirect


# Create your views here.
@login_required(login_url="login/")
def home(request):
	data = IncidentLog.objects.all()
	f = LogFilter(request.GET, queryset=data)
	table = LogTable(f.qs)
	table.order_by = '-timestamp'
	RequestConfig(request).configure(table)
	return render(request, 'home.html', {'table': table, 'filter': f})
def incident(request, id):
	incident = IncidentLog.objects.get(id=id) 
	form = IncidentForm(request.POST or None, instance=incident)
	if request.method == 'POST':
		if form.is_valid():
			form.save()
			return redirect('home')
	return render(request, 'incident.html', {'form': form})
def accounts(request):
	return render(request, 'accounts.html', {})
