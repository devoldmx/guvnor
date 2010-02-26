package org.drools.guvnor.server.contenthandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.drools.compiler.DroolsParserException;
import org.drools.guvnor.client.factmodel.FactMetaModel;
import org.drools.guvnor.client.factmodel.FactModels;
import org.drools.guvnor.client.factmodel.FieldMetaModel;
import org.drools.guvnor.client.rpc.RuleAsset;
import org.drools.guvnor.client.rpc.RuleContentText;
import org.drools.guvnor.server.ServiceImplementation;
import org.drools.guvnor.server.util.TestEnvironmentSessionHelper;
import org.drools.repository.AssetItem;
import org.drools.repository.PackageItem;
import org.drools.repository.RulesRepository;

public class ModelContentHandlerTest extends TestCase {

    public void testModelAttached() throws Exception {
        RulesRepository repo = new RulesRepository( TestEnvironmentSessionHelper.getSession() );
        PackageItem pacakge = repo.createPackage("testModelAttachedPack", "for test");
        AssetItem asset = pacakge.addAsset("testModelAttachedAsset", "description");
        
        InputStream is = this.getClass().getResourceAsStream("domain.objects-1.1.8.jar");
        asset.updateBinaryContentAttachment(is);
        
        ModelContentHandler modelContentHandler = new ModelContentHandler();
        modelContentHandler.modelAttached(asset);

        String header = ServiceImplementation.getDroolsHeader( pacakge );
        assertTrue(header.indexOf("package-info.class") == -1);
    }

 }

