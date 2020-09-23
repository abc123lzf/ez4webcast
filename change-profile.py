#!/usr/bin/python
# -*- coding: UTF-8 -*-
# 用于切换pom.xml的profile
# 使用方法：change-profile.py profile

import sys
from xml.etree.ElementTree import Element
import xml.etree.ElementTree as ET


def create_activation_element():
    active_element = Element('activation')
    active_by_default_element = Element('activeByDefault')
    active_by_default_element.text = 'true'
    active_element.append(active_by_default_element)
    return active_element


if __name__ == '__main__':
    ET.register_namespace('', "http://maven.apache.org/POM/4.0.0")
    ET.register_namespace('xsi', "http://www.w3.org/2001/XMLSchema-instance")

    if len(sys.argv) == 1:
        print """
        Usage: change-env.py arg
        arg is active profile (must exists in pom.xml)
        """
        exit(1)

    active_id = sys.argv[1]
    source_pom = ET.parse('pom.xml')
    profiles_ele = source_pom.getroot().find('{http://maven.apache.org/POM/4.0.0}profiles')

    profile_map = {}
    now_active = None

    for profile in profiles_ele.findall('{http://maven.apache.org/POM/4.0.0}profile'):
        profile_id = profile.find('{http://maven.apache.org/POM/4.0.0}id').text
        profile_map[profile_id] = profile
        active_ele = profile.find('{http://maven.apache.org/POM/4.0.0}activation')
        if active_ele is None:
            continue
        if active_ele.find('{http://maven.apache.org/POM/4.0.0}activeByDefault').text == 'true':
            now_active = profile_id

    if active_id not in profile_map:
        print 'Profile [' + active_id + '] not exists in pom.xml'
        exit(1)

    if active_id == now_active:
        print 'Profile [' + active_id + '] has active'
        exit(0)

    new_element = create_activation_element()

    if now_active is not None:
        ele = profile_map[now_active]
        ele.remove(ele.find('{http://maven.apache.org/POM/4.0.0}activation'))

    ele = profile_map[active_id]
    ele.append(new_element)

    source_pom.write('pom.xml', 'UTF-8')
    print 'Profile is change to [' + active_id + ']'

