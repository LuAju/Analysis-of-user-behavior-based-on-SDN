#!/bin/bash

##### 5M #####

	 ##Set enp5s0  10M
 	 ovs-vsctl set interface enp5s0 ingress_policing_rate=10000   
	 ovs-vsctl set interface enp5s0 ingress_policing_burst=10
	 
	 ##Set enp5s1   5M 
 	 ovs-vsctl set interface enp5s1 ingress_policing_rate=5000
	 ovs-vsctl set interface enp5s1 ingress_policing_burst=10
	 
	 ##Set enp5s2 
 	 ##ovs-vsctl set interface enp5s2 ingress_policing_rate=1000
	 ##ovs-vsctl set interface enp5s2 ingress_policing_burst=10
