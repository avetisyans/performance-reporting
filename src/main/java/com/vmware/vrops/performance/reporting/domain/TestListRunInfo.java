package com.vmware.vrops.performance.reporting.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.vmware.vrops.performance.reporting.domain.TestRunInfo.TestResult;


//@Entity
@Table(name="test_list_run_info")
public class TestListRunInfo implements Serializable{

   /**
    *
    */
   private static final long serialVersionUID = -3960209790438851910L;

   @Id
   @GeneratedValue
   private Long id;

   @OneToMany(fetch=FetchType.EAGER ,cascade=CascadeType.ALL, mappedBy="testListRunInfo")
   //@JsonManagedReference
   Set<TestRunInfo> testRunInfos = new LinkedHashSet<TestRunInfo>();

/*   @OneToOne(cascade=CascadeType.ALL)
   private TestDef testDef;*/

   @Transient
   private Double successMax;

   @Transient
   private Double successMin;

   @Transient
   private Double successAvg;

   private Double max;

   private Double min;

   private Double avg;

   private int numberOfRuns;

   private int totalPass;

   @ManyToOne
   @JoinColumn(name="testSuite_id")
  // @JsonBackReference
   private TestSuite testSuite;

   public TestListRunInfo() {

   }

   public Long getId() {
      return id;
   }


   public Set<TestRunInfo> getTestRunInfos() {
      return testRunInfos;
   }

   public void setTestRunInfos(Set<TestRunInfo> testRunInfos) {
      this.testRunInfos = testRunInfos;
   }

   public TestSuite getTestSuite() {
      return testSuite;
   }

   public void setTestSuite(TestSuite testSuite) {
      this.testSuite = testSuite;
   }

   public Double getSuccessMax() {

	   List<TestRunInfo> successTestRunInfos = getSuccessTestRunInfos();
	   for(TestRunInfo testRunInfo : successTestRunInfos) {
			   if(successMax < testRunInfo.getExecutionTime()) {
				   successMax = testRunInfo.getExecutionTime();
			   }
	   }
	   return successMax;
   }

   public Double getSuccessMin() {
	   List<TestRunInfo> successTestRunInfos = getSuccessTestRunInfos();
	   for(TestRunInfo testRunInfo : successTestRunInfos) {
			   if(successMin > testRunInfo.getExecutionTime()) {
				   successMin = testRunInfo.getExecutionTime();
			   }
	   }
	   return successMin;
   }

   public Double getSuccessAvg() {
	   List<TestRunInfo> successTestRunInfos = getSuccessTestRunInfos();
	   Double successSum = 0.0;
	   for(TestRunInfo testRunInfo : successTestRunInfos) {
		   successSum += testRunInfo.getExecutionTime();
	   }
	   if(!successTestRunInfos.isEmpty()) {
		   successAvg = successSum/successTestRunInfos.size();
	   }
	   return successAvg;
   }

   public Double getMax() {
      return max;
   }

   public void setMax(Double max) {
      this.max = max;
   }

   public Double getMin() {
      return min;
   }

   public void setMin(Double min) {
      this.min = min;
   }

   public Double getAvg() {
      return avg;
   }

   public void setAvg(Double avg) {
      this.avg = avg;
   }


   public int getNumberOfRuns() {
      return numberOfRuns;
   }

   public void setNumberOfRuns(int numberOfRuns) {
      this.numberOfRuns = numberOfRuns;
   }

   public int getTotalPass() {
      return totalPass;
   }

/*   public TestDef getTestDef() {
	return testDef;
}

	public void setTestDef(TestDef testDef) {
		this.testDef = testDef;
	}*/

	public void setTotalPass(int totalPass) {
	      this.totalPass = totalPass;
   }

	private List<TestRunInfo> getSuccessTestRunInfos() {

		List<TestRunInfo> successTestRunInfos = new ArrayList<TestRunInfo>();
		for(TestRunInfo testRunInfo : testRunInfos) {
			   if(testRunInfo.getTestResult().equals(TestResult.PASS)) {
				   successTestRunInfos.add(testRunInfo);
			   }
		}
		return successTestRunInfos;
	}

	public void setSuccessMax(Double successMax) {
		this.successMax = successMax;
	}

	public void setSuccessMin(Double successMin) {
		this.successMin = successMin;
	}

	public void setSuccessAvg(Double successAvg) {
		this.successAvg = successAvg;
	}



}
