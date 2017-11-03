from django.contrib.auth.forms import AuthenticationForm 
from django import forms
from reporter.models import IncidentLog

class LoginForm(AuthenticationForm):
    username = forms.CharField(label="Username", max_length=30, 
                               widget=forms.TextInput(attrs={'class': 'form-control', 'name': 'username'}))
    password = forms.CharField(label="Password", max_length=30, 
			       widget=forms.PasswordInput(attrs={'class': 'form-control', 'name': 'password'}))
class IncidentForm(forms.ModelForm):
    class Meta:
        model = IncidentLog
        fields = ['title','description','priority','is_resolved']

        
    PRIORITY_CHOICES = (
        (0, '0'),
        (1, '1'),
        (2, '2'),
        (3, '3'),
        (4, '4'),
    )
    title = forms.CharField(label='Title', max_length=45, widget=forms.TextInput(attrs={'class': 'form-control', }))
    description = forms.CharField(label='Description', max_length=350, widget=forms.Textarea(attrs={'class': 'form-control', }))
    priority = forms.ChoiceField(choices=PRIORITY_CHOICES, label='Priority')
    is_resolved = forms.BooleanField(label='Resolved', required=False)
