#!/bin/bash

##### 8M #####

	 ##Set enp5s0 
 	# ovs-vsctl set interface enp5s0 ingress_policing_rate=16000
	# ovs-vsctl set interface enp5s0 ingress_policing_burst=10
	 
	 ##Set enp5s1 
 	# ovs-vsctl set interface enp5s1 ingress_policing_rate=8000
	# ovs-vsctl set interface enp5s1 ingress_policing_burst=10
	 
	 ##Set enp5s2 
 	 ##ovs-vsctl set interface enp5s2 ingress_policing_rate=2000
	 ##ovs-vsctl set interface enp5s2 ingress_policing_burst=10