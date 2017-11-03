import django_tables2 as tables
from reporter.models import IncidentLog
from django_filters.views import FilterView
from django_tables2.views import SingleTableView 
from reporter.filters import LogFilter

class LogTable(tables.Table):
    more_info = tables.TemplateColumn('<a href="{% url \'incident\' record.id %}">Review</a>')

    class Meta:
        model = IncidentLog
        # Change 'paleblue' to 'table' to use inbuilt bootstrap CSS class
        attrs = {'class': 'table table-bordered table-striped', 'style': 'background-color: #FFFFFF; border-radius: 8px;'}
        sequence = ('id', 'user', 'title', 'description', 'timestamp', 'priority', 'is_resolved')




