package org.apache.hadoop.security;

import org.apache.hadoop.conf.Configuration;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import static org.apache.hadoop.fs.CommonConfigurationKeysPublic.HADOOP_SECURITY_CONFIGURATIONBASED_GROUP_MAPPING_FILE;

/**
 * Created by user on 01/02/2017.
 */
public class TestConfigurationBasedGroupsMapping {

  @Test
  public void testNoConfiguration() {

    ConfigurationBasedGroupsMapping mapping = new ConfigurationBasedGroupsMapping();
    Assert.assertTrue(mapping.getUser2groups().isEmpty());
  }

  @Test
  public void testWrongConfiguration() {
    Configuration conf = new Configuration();
    conf.set(HADOOP_SECURITY_CONFIGURATIONBASED_GROUP_MAPPING_FILE, "filenotexists");

    ConfigurationBasedGroupsMapping mapping = new ConfigurationBasedGroupsMapping(conf);
    Assert.assertTrue(mapping.getUser2groups().isEmpty());
  }

  @Test
  public void testConfigurationBasedGroupsMapping() throws Exception {

    // find test configuration file
    String fileName = TestConfigurationBasedGroupsMapping.class.getClassLoader().getResource("test-user2groups.txt")
            .getPath();

    Configuration conf = new Configuration();
    conf.set(HADOOP_SECURITY_CONFIGURATIONBASED_GROUP_MAPPING_FILE, fileName);

    ConfigurationBasedGroupsMapping mapping = new ConfigurationBasedGroupsMapping(conf);

    Assert.assertEquals(2, mapping.getUser2groups().keySet().size());

    // test user2
    Set<String> groups1 = mapping.getGroups("user2");
    Assert.assertEquals(1, groups1.size());
    groups1.contains("group1");

    // test user3
    Set<String> groups2 = mapping.getGroups("user3");
    Assert.assertEquals(2, groups2.size());
    groups2.contains("group2");
    groups2.contains("group3");

    // test user0
    Set<String> groups3 = mapping.getGroups("user0");
    Assert.assertTrue(groups3.isEmpty());

    // test
    Set<String> nongroups = mapping.getGroups("notexitsuser");
    Assert.assertTrue(nongroups.isEmpty());
  }
}
