/*******************************************************************************
 * Copyright (c) 2014-2019 University of Southampton and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package ac.soton.eventb.classdiagrams.diagram.sheet.custom;

import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.IFilter;
import org.eventb.emf.core.CorePackage;
import org.eventb.emf.core.machine.Machine;

import ac.soton.eventb.classdiagrams.Class;
import ac.soton.eventb.classdiagrams.ClassdiagramsPackage;
import ac.soton.eventb.emf.diagrams.sheet.AbstractEditTableWithDefaultNamingPropertySection;
import ac.soton.eventb.emf.diagrams.util.custom.DiagramUtils;


public class ConstraintsPropertySection extends AbstractEditTableWithDefaultNamingPropertySection {

	/**
	 * Element Filter for this property section.
	 */
	public static final class Filter implements IFilter {
		@Override
		public boolean select(Object toTest) {
			return DiagramUtils.unwrap(toTest) instanceof Class;
		}
	}

	@Override
	protected EReference getFeature() {
		return ClassdiagramsPackage.eINSTANCE.getClass_Constraints();
	}

	@Override
	protected EStructuralFeature getFeatureForCol(final int col) {
		switch (col) {
		case 0 : return CorePackage.eINSTANCE.getEventBNamed_Name();
		case 1 : return ClassdiagramsPackage.Literals.CLASS_CONSTRAINT__COMPONENT;
		case 2 : return CorePackage.eINSTANCE.getEventBPredicate_Predicate();
		case 3 : return CorePackage.eINSTANCE.getEventBDerived_Theorem();
		case 4 : return	CorePackage.eINSTANCE.getEventBCommented_Comment();
		default : return null;
		}
	}

	@Override
	protected boolean isMulti(final int col){
		return col==2 || col==4 ;
	}
	
	
	@Override
	protected boolean isRodinKeyboard(final int col) {
		return  col == 2;
	}
	
	@Override
	protected boolean isReadOnly(final int col) {
		return  false;
	}

	@Override
	protected int columnWidth(final int col){
		switch (col) {
		case 0 : return 150;	//name field
		case 1 : return 100;	//component field
		case 2 : return 400;	//predicate field
		case 3 : return 100;	//derived field
		case 4 : return 400;	//comment field
		default : return -1;	//unknown
		}
	}

	@Override
	protected String getButtonLabelText() {
		return getTranslationTarget() instanceof Machine? 
				"Invariant" : "Axiom";
	}
	
	@Override
	protected String getFeaturePrefix() {
		return getTranslationTarget() instanceof Machine? 
				"inv" : "axm";
	}
	
	@Override
	protected List<?> getPossibleValues(final int col){
		if (col==1) {
			return this.getComponentsInScope();
		}else
			return super.getPossibleValues(col);
	}

}
