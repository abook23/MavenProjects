package com.grp.ln.web.model;

import java.util.ArrayList;
import java.util.List;

public class MediaMsgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MediaMsgExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMmsgIdIsNull() {
            addCriterion("mmsgId is null");
            return (Criteria) this;
        }

        public Criteria andMmsgIdIsNotNull() {
            addCriterion("mmsgId is not null");
            return (Criteria) this;
        }

        public Criteria andMmsgIdEqualTo(String value) {
            addCriterion("mmsgId =", value, "mmsgId");
            return (Criteria) this;
        }

        public Criteria andMmsgIdNotEqualTo(String value) {
            addCriterion("mmsgId <>", value, "mmsgId");
            return (Criteria) this;
        }

        public Criteria andMmsgIdGreaterThan(String value) {
            addCriterion("mmsgId >", value, "mmsgId");
            return (Criteria) this;
        }

        public Criteria andMmsgIdGreaterThanOrEqualTo(String value) {
            addCriterion("mmsgId >=", value, "mmsgId");
            return (Criteria) this;
        }

        public Criteria andMmsgIdLessThan(String value) {
            addCriterion("mmsgId <", value, "mmsgId");
            return (Criteria) this;
        }

        public Criteria andMmsgIdLessThanOrEqualTo(String value) {
            addCriterion("mmsgId <=", value, "mmsgId");
            return (Criteria) this;
        }

        public Criteria andMmsgIdLike(String value) {
            addCriterion("mmsgId like", value, "mmsgId");
            return (Criteria) this;
        }

        public Criteria andMmsgIdNotLike(String value) {
            addCriterion("mmsgId not like", value, "mmsgId");
            return (Criteria) this;
        }

        public Criteria andMmsgIdIn(List<String> values) {
            addCriterion("mmsgId in", values, "mmsgId");
            return (Criteria) this;
        }

        public Criteria andMmsgIdNotIn(List<String> values) {
            addCriterion("mmsgId not in", values, "mmsgId");
            return (Criteria) this;
        }

        public Criteria andMmsgIdBetween(String value1, String value2) {
            addCriterion("mmsgId between", value1, value2, "mmsgId");
            return (Criteria) this;
        }

        public Criteria andMmsgIdNotBetween(String value1, String value2) {
            addCriterion("mmsgId not between", value1, value2, "mmsgId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("userId =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("userId <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("userId >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("userId >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("userId <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("userId <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("userId like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("userId not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("userId in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("userId not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("userId between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("userId not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andRes_idsIsNull() {
            addCriterion("res_ids is null");
            return (Criteria) this;
        }

        public Criteria andRes_idsIsNotNull() {
            addCriterion("res_ids is not null");
            return (Criteria) this;
        }

        public Criteria andRes_idsEqualTo(String value) {
            addCriterion("res_ids =", value, "res_ids");
            return (Criteria) this;
        }

        public Criteria andRes_idsNotEqualTo(String value) {
            addCriterion("res_ids <>", value, "res_ids");
            return (Criteria) this;
        }

        public Criteria andRes_idsGreaterThan(String value) {
            addCriterion("res_ids >", value, "res_ids");
            return (Criteria) this;
        }

        public Criteria andRes_idsGreaterThanOrEqualTo(String value) {
            addCriterion("res_ids >=", value, "res_ids");
            return (Criteria) this;
        }

        public Criteria andRes_idsLessThan(String value) {
            addCriterion("res_ids <", value, "res_ids");
            return (Criteria) this;
        }

        public Criteria andRes_idsLessThanOrEqualTo(String value) {
            addCriterion("res_ids <=", value, "res_ids");
            return (Criteria) this;
        }

        public Criteria andRes_idsLike(String value) {
            addCriterion("res_ids like", value, "res_ids");
            return (Criteria) this;
        }

        public Criteria andRes_idsNotLike(String value) {
            addCriterion("res_ids not like", value, "res_ids");
            return (Criteria) this;
        }

        public Criteria andRes_idsIn(List<String> values) {
            addCriterion("res_ids in", values, "res_ids");
            return (Criteria) this;
        }

        public Criteria andRes_idsNotIn(List<String> values) {
            addCriterion("res_ids not in", values, "res_ids");
            return (Criteria) this;
        }

        public Criteria andRes_idsBetween(String value1, String value2) {
            addCriterion("res_ids between", value1, value2, "res_ids");
            return (Criteria) this;
        }

        public Criteria andRes_idsNotBetween(String value1, String value2) {
            addCriterion("res_ids not between", value1, value2, "res_ids");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andReadNumberIsNull() {
            addCriterion("readNumber is null");
            return (Criteria) this;
        }

        public Criteria andReadNumberIsNotNull() {
            addCriterion("readNumber is not null");
            return (Criteria) this;
        }

        public Criteria andReadNumberEqualTo(Integer value) {
            addCriterion("readNumber =", value, "readNumber");
            return (Criteria) this;
        }

        public Criteria andReadNumberNotEqualTo(Integer value) {
            addCriterion("readNumber <>", value, "readNumber");
            return (Criteria) this;
        }

        public Criteria andReadNumberGreaterThan(Integer value) {
            addCriterion("readNumber >", value, "readNumber");
            return (Criteria) this;
        }

        public Criteria andReadNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("readNumber >=", value, "readNumber");
            return (Criteria) this;
        }

        public Criteria andReadNumberLessThan(Integer value) {
            addCriterion("readNumber <", value, "readNumber");
            return (Criteria) this;
        }

        public Criteria andReadNumberLessThanOrEqualTo(Integer value) {
            addCriterion("readNumber <=", value, "readNumber");
            return (Criteria) this;
        }

        public Criteria andReadNumberIn(List<Integer> values) {
            addCriterion("readNumber in", values, "readNumber");
            return (Criteria) this;
        }

        public Criteria andReadNumberNotIn(List<Integer> values) {
            addCriterion("readNumber not in", values, "readNumber");
            return (Criteria) this;
        }

        public Criteria andReadNumberBetween(Integer value1, Integer value2) {
            addCriterion("readNumber between", value1, value2, "readNumber");
            return (Criteria) this;
        }

        public Criteria andReadNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("readNumber not between", value1, value2, "readNumber");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}