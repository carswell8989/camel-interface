/*
 * AJ-Rent ERP Car version 1.0
 *
 * Copyright ⓒ 2017 AJ Rent Car. All rights reserved.
 *
 * This is a proprietary software of AJ Rent Car corp, and you may not use this file except in
 * compliance with license agreement with AJ Rent Car corp corp. Any redistribution or use of this
 * software, with or without modification shall be strictly prohibited without prior written
 * approval of AJ Rent Carcorp, and the copyright notice above does not evidence any actual or
 * intended publication of such software
 *
 */

package kr.co.camel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 클래스(인터페이스)에 대한 상세설명
 *
 * @author Seulki
 * @since 2018. 5. 29.
 * @version 1.0
 * @see
 *
 * <pre>
 *
 * 개정이력(Modification Information)
 *
 * 수정일      수정자   수정내용
 * --------------------------------------------------
 * 2018. 5. 29.    Seulki    최초 생성
 *
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HttpOnline {
}
