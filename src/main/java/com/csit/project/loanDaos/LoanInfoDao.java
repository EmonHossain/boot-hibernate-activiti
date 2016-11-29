package com.csit.project.loanDaos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.csit.project.enetities.LoanInfo;

@Repository
@Transactional
public class LoanInfoDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(LoanInfo loanInfo) {
		getSession().save(loanInfo);
	}

	public void delete(LoanInfo loanInfo) {
		getSession().delete(loanInfo);
	}

	public LoanInfo getLoanInfoByUserId(String taskId) {

		return (LoanInfo) getSession().createQuery("from LoanInfo where taskId=:taskId").setParameter("taskId", taskId)
				.uniqueResult();
	}

	public void updateLoanInfo(LoanInfo loanInfo) {
		getSession().update(loanInfo);

	}

	@SuppressWarnings("unchecked")
	public List<LoanInfo> getAllUnresolvebLoanByAssinee(String assignee) {

		Criteria criteria = getSession().createCriteria(LoanInfo.class);

		Criterion assign = Restrictions.eq("assignee", assignee);
		Criterion approve = Restrictions.isNull("resolvedBy");

		LogicalExpression exp = Restrictions.and(assign, approve);
		criteria.add(exp);

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<LoanInfo> getResolvedLoanList(String approver) {
		Criteria criteria = getSession().createCriteria(LoanInfo.class);

		Criterion assign = Restrictions.eq("assignee", approver);
		Criterion notNull = Restrictions.isNotNull("resolvedBy");

		LogicalExpression lexp = Restrictions.and(assign, notNull);
		criteria.add(lexp);

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<LoanInfo> getLoanInfoByAssigneeName(String approver) {
		List<LoanInfo> loans = getSession().createQuery("from LoanInfo where requestedTo =: requestedTo")
				.setParameter("requestedTo", approver).list();

		return loans;
	}

}
