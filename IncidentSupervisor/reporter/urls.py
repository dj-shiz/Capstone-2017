from django.conf.urls import url
from . import views

urlpatterns = [
	url(r'^$', views.home, name='home'),
	url(r'^incident/(?P<id>\d+)/$', views.incident, name='incident'),
	url(r'^accounts/$', views.accounts, name='accounts'),
]
