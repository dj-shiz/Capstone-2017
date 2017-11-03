from django_filters import FilterSet, ChoiceFilter
from django import forms

import django_filters
from reporter.models import IncidentLog

class LogFilter(FilterSet):
	RES_CHOICES = (
		(0, 'Unresolved'),
		(1, 'Resolved'),
	)

	is_resolved = ChoiceFilter(choices=RES_CHOICES, empty_label='All')
	title = django_filters.CharFilter(lookup_expr='icontains')
	description = django_filters.CharFilter(lookup_expr='icontains')

	class Meta:
		model = IncidentLog
		fields = ['is_resolved', 'title', 'description']
